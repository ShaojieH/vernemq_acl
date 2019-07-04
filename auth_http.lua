require "auth/auth_commons"

BASE_URL = "http://host.docker.internal:8080/api/v1/vernemq/"
REG_URL = BASE_URL.."reg"
PUB_URL = BASE_URL.."pub"
SUB_URL = BASE_URL.."sub"

function auth_on_register(reg)
    if reg.username ~= nil and reg.password ~= nil then
        data = json.encode({
            mountpoint = reg.mountpoint,
            client_id = reg.client_id, 
            username = reg.username,
            password = reg.password
        })
        headers = {}
        headers["x_post_header"] = "X-POST-HEADER"
        headers['Accept'] = "application/json"
        headers["Content-Type"] = "application/json"
        ret = http.post(pool, REG_URL, data, headers)
        if ret.status == 200 and ret.ref then
            --[[ test no cache in vernemq
            body = http.body(ret.ref) 
            json = json.decode(body)
            
            cache_insert(
                reg.mountpoint, 
                reg.client_id, 
                reg.username,
                json.publish_acl,
                json.subscribe_acl
                )
            ]]--
            return true
        else
            return false
        end
    end
    return false
end

function auth_on_publish(pub)
    if pub.username ~= nil then
        data = json.encode({
            mountpoint = pub.mountpoint,
            client_id = pub.client_id, 
            username = pub.username,
            qos = pub.qos,
            topic = pub.topic,
            payload = pub.payload,
            retain = pub.retain
        })
        headers = {}
        headers["x_post_header"] = "X-POST-HEADER"
        headers['Accept'] = "application/json"
        headers["Content-Type"] = "application/json"
        ret = http.post(pool, PUB_URL, data, headers)
        if ret.status == 200 and ret.ref then
            return true
        else
            return false
        end
    end
    return false
end


function formatTopics(l)
    for _, sub in ipairs(l) do
        sub.topic = sub[1]
        sub.qos = sub[2]
        sub[1]=nil
        sub[2]=nil
    end
    return l
end

function auth_on_subscribe(sub)
    if sub.username ~= nil then
        data = json.encode({
            mountpoint = sub.mountpoint,
            client_id = sub.client_id, 
            username = sub.username,
            topics = formatTopics(sub.topics)
        })
        headers = {}
        headers["x_post_header"] = "X-POST-HEADER"
        headers['Accept'] = "application/json"
        headers["Content-Type"] = "application/json"
        ret = http.post(pool, SUB_URL, data, headers)
        if ret.status == 200 and ret.ref then
            return true
        else
            return false
        end
    end
    return false
end

pool = "auth_http"
config = {
    pool_id = pool
}

http.ensure_pool(config)

hooks = {
    auth_on_register = auth_on_register,
    auth_on_publish = auth_on_publish,
    auth_on_subscribe = auth_on_subscribe,
    --on_unsubscribe = on_unsubscribe,
    --on_client_gone = on_client_gone,
    --on_client_offline = on_client_offline,

    --auth_on_register_m5 = auth_on_register_m5,
    --auth_on_publish_m5 = auth_on_publish_m5,
    --auth_on_subscribe_m5 = auth_on_subscribe_m5,
}
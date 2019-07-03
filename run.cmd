docker run -p 1885:1885 ^
  --rm --name vernemq ^
  -e "DOCKER_VERNEMQ_ALLOW_ANONYMOUS=off" ^
  -e "DOCKER_VERNEMQ_PLUGINS__VMQ_PASSWD=off" ^
  -e "DOCKER_VERNEMQ_PLUGINS__VMQ_ACL=off" ^
  -e "DOCKER_VERNEMQ_PLUGINS__VMQ_DIVERSITY=on" ^
  -e "DOCKER_VERNEMQ_VMQ_DIVERSITY__HTTP_AUTH__file=/auth_http.lua" ^
  -v /auth_http.lua:/auth_http.lua ^
  erlio/docker-vernemq
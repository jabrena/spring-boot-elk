#!/bin/bash

api_base_url=http://localhost:8001

for i in {0..1000}
 do
  printf "Iteration # %s...\n" "${i}"
  curl "${api_base_url}/posts/resttemplate/1"
  curl "${api_base_url}/posts/webclient/1"
  curl "${api_base_url}/katakroker"
  curl "${api_base_url}/posts/resttemplate/2"
  curl "${api_base_url}/posts/webclient/2"
  printf "\n"
 done
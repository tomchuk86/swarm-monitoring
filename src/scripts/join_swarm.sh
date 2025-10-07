#!/bin/bash
set -e

# Читаем токен и подключаемся
TOKEN=$(cat /vagrant/worker_token.txt)
docker swarm join --token $TOKEN 192.168.56.10:2377

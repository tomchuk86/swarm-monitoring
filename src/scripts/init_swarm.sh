#!/bin/bash
set -e

# Инициализируем Swarm
docker swarm init --advertise-addr 192.168.56.10

# Генерируем токены
docker swarm join-token worker -q > /vagrant/worker_token.txt

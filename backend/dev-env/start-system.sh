#!/bin/bash
sudo systemctl start postgresql.service
sudo systemctl start docker.service
docker-compose up -d
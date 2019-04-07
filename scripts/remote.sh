#! /bin/bash

echo "[REMOTE]: Cleaning up..."
rm -rf /home/www/resources
mkdir -p /home/www/resources/$1
echo "[REMOTE]: Extracting the package..."
cd /home/www/resources/$1
tar zxf /home/www/tmp/package.tar.gz
echo "[REMOTE]: Installing the content..."
cp -rv /home/www/resources/$1 /home/www/public/
chmod 755 /home/www/public/ -R
echo "[REMOTE]: Cleaning up..."
cd ~
rm -rf /home/www/resources /home/www/tmp/
mkdir -p /home/www/tmp
echo "[REMOTE]: Done"

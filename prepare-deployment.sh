rm target/deployment.yml
sed -e "s+{{IMAGE_VERSION}}+$2+g" deployment.yml >> target/deployment.yml
#!/bin/bash 

# Step 1: Run Postman CLI to execute the collection
node runPostman.js

# Step 2: Start ZAP in the background using Docker
docker run -d -p 8080:8080 -v $(pwd):/zap/wrk/:rw zaproxy/zap-stable zap.sh -daemon \
  -config api.key=critgiiksqvdcmb9jou3i2akl3 -port 8080

# Wait for ZAP to fully start
sleep 10

# Step 3: Loop through each base URL and trigger a scan
while IFS= read -r URL; do
    echo "Scanning: $URL"
    curl "http://localhost:8080/JSON/ascan/action/scan/?url=$URL&apikey=critgiiksqvdcmb9jou3i2akl3"
done < base-urls.txt 

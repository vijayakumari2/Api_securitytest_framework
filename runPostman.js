const { execSync } = require('child_process');
const fs = require('fs');

// Define paths for the Postman collection and environment
const collectionFile = '4532654-79c463b2-9f60-4062-831a-786c2f097ab7';
const environmentFile = '4532654-34f35f95-df4d-494b-ac47-21b4791d7883';

// Define your API key
const apiKey = 'PMAK-66f123d936c09100010e630b-3daeba718f17494901163817cf0cf613ed'; // Ensure to set this environment variable

try {
    // Log in using the API key
    console.log('Logging into Postman...');
    execSync(`postman login --with-api-key ${apiKey}`, { stdio: 'inherit' });

    // Run Postman CLI to execute the collection
    console.log('Running Postman collection...');
    execSync(`postman collection run ${collectionFile} -e ${environmentFile} --reporters cli`, { stdio: 'inherit' });

    console.log('Postman collection executed successfully.');

    // Read the collection file and extract base URLs
    console.log('Extracting base URLs from the collection...');
    const collection = JSON.parse(fs.readFileSync(collectionFile, 'utf8'));
    const baseUrls = new Set();

    collection.item.forEach(item => {
        if (item.request && item.request.url) {
            const url = item.request.url.raw || item.request.url;
            const baseUrl = new URL(url).origin; // Get the base URL
            baseUrls.add(baseUrl);
        }
    });

    // Save base URLs to a file
    fs.writeFileSync('base-urls.txt', [...baseUrls].join('\n'));
    console.log('Base URLs extracted:', [...baseUrls]);
} catch (error) {
    console.error('Error running Postman collection:', error);
}

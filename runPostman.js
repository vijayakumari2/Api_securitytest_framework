const { execSync } = require('child_process');
const fs = require('fs');

// Define paths for the Postman collection and environment
const collectionFile = '<path-to-your-collection-file>.json';
const environmentFile = '<path-to-your-environment-file>.json';

// Run Postman CLI to execute the collection
try {
    execSync(`postman run ${collectionFile} --environment ${environmentFile} --reporters cli`, { stdio: 'inherit' });
} catch (error) {
    console.error('Error running Postman collection:', error);
}

// Read the collection file and extract base URLs
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

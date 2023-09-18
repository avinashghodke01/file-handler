# file-handler
file handler takes input as list of file names and add dummy text in each line of file.

# build steps
1. create input and output directory at location where its mentioned in properties file
2. add files that you want to process in put directory
3. execute /modify api to process files, output will be in /output folder in location mentioned in properties file

# api operation
URL: /filehandler/modify
Operation: POST
Request:
```
{
    "data": [
        "C:\\temp\\filehandler\\input\\business-profile-data.txt",
        "C:\\temp\\filehandler\\input\\customer-retail-data.txt",
        "C:\\temp\\filehandler\\input\\premium-customer-leads.txt"
    ]
}
```

Response:
```
{
    "message": "Files modified successfully!",
    "outputiles": [
        "C:\\temp\\filehandler\\output\\20230918\\business-profile-data.txt",
        "C:\\temp\\filehandler\\output\\20230918\\customer-retail-data.txt",
        "C:\\temp\\filehandler\\output\\20230918\\premium-customer-leads.txt"
    ]
}
```

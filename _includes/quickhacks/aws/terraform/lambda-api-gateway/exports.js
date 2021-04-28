exports.handler = (event, context, callback) => {

    const responseBody = {
        "quickhacks": "Lambda (API Gateway)"
    };

    const response = {
        "statusCode": 200,
        "headers": {
            "quickhacks": "lambda_api_gateway"
        },
        "body": JSON.stringify(responseBody),
        "isBase64Encoded": false
    };

    callback(null, response);
};

import os
from mistralai import Mistral

# Get API key from environment variable
api_key = os.environ.get("MISTRAL_API_KEY")

if not api_key:
    raise ValueError("MISTRAL_API_KEY not set")

# Create client
client = Mistral(api_key=api_key)

# Make request
response = client.chat.complete(
    model="ministral-3b-latest",
    messages=[
        {"role": "user", "content": "Loop syntax and example in Python."}
    ],
    temperature=1
)

# Print response
print(response.choices[0].message.content)

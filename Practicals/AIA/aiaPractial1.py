from openai import OpenAI

# setx OPENAI_API_KEY "sk-your_actual_api_key_here"
# client = OpenAI() Put your API KEy here
client = OpenAI(api_key="")

response = client.responses.create(
  model="gpt-4.1-mini",
  input="write C language loop syntax, example.",
  store=True,
)

print(response.output_text)
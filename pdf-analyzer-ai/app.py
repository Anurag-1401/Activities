import streamlit as st
import fitz
import pytesseract
import cv2
import numpy as np
from PIL import Image
from langchain_groq import ChatGroq
import os

# ---------- API KEY ----------
groq_key = os.getenv("GROQ_API_KEY")

if not groq_key:
    st.error("❌ GROQ API KEY NOT FOUND")
    st.stop()

llm = ChatGroq(
    model="llama-3.3-70b-versatile",
    api_key=groq_key
)

# ---------- SESSION STATE ----------
if "text" not in st.session_state:
    st.session_state.text = ""

if "summary" not in st.session_state:
    st.session_state.summary = None

if "chat_history" not in st.session_state:
    st.session_state.chat_history = []

# ---------- PDF ----------
def extract_pdf(file):
    text = ""
    doc = fitz.open(stream=file.read(), filetype="pdf")
    for page in doc:
        text += page.get_text()
    return text

# ---------- IMAGE ----------
def extract_image_text(file):
    file_bytes = np.asarray(bytearray(file.read()), dtype=np.uint8)
    img = cv2.imdecode(file_bytes, 1)
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    text = pytesseract.image_to_string(gray)
    return text

# ---------- LLM ----------
def summarize(text):
    try:
        prompt = f"""
        Give detailed structured summary:
        Title, Abstract, Concepts, Methodology, Results, Conclusion, Keywords
        {text[:8000]}
        """
        return llm.invoke(prompt).content
    except Exception as e:
        return f"❌ Error: {str(e)}"

def qa(text, question):
    try:
        prompt = f"""
        Answer from given content:
        {text[:8000]}
        Question: {question}
        """
        return llm.invoke(prompt).content
    except Exception as e:
        return f"❌ Error: {str(e)}"

def analyze_image(text):
    try:
        prompt = f"""
        Analyze this extracted image text:
        Explain, summarize, and give insights.
        {text}
        """
        return llm.invoke(prompt).content
    except Exception as e:
        return f"❌ Error: {str(e)}"

# ---------- UI ----------
st.title("🧠 Multimodal AI Analyzer (PDF + Image)")

file = st.file_uploader("Upload PDF or Image", type=["pdf", "png", "jpg", "jpeg"])

if file:
    file_type = file.type

    if "pdf" in file_type:
        st.session_state.text = extract_pdf(file)
        st.success("PDF processed!")

    else:
        st.session_state.text = extract_image_text(file)
        st.image(file, caption="Uploaded Image", use_column_width=True)
        st.success("Image processed!")

# ---------- SUMMARY ----------
if st.button("Generate Summary"):
    if st.session_state.text:
        st.session_state.summary = summarize(st.session_state.text)

# ✅ ALWAYS SHOW SUMMARY
if st.session_state.summary:
    st.subheader("📌 Summary")
    st.write(st.session_state.summary)

# ---------- IMAGE ANALYSIS ----------
if file and "image" in file.type:
    if st.button("Analyze Image"):
        result = analyze_image(st.session_state.text)
        st.subheader("🖼️ Image Analysis")
        st.write(result)

# ---------- CHAT ----------
st.subheader("💬 Chat with Document")

question = st.chat_input("Ask something about the document...")

if question:
    answer = qa(st.session_state.text, question)

    st.session_state.chat_history.append({
        "question": question,
        "answer": answer
    })

# ---------- DISPLAY CHAT ----------
for chat in st.session_state.chat_history:
    with st.chat_message("user"):
        st.write(chat["question"])

    with st.chat_message("assistant"):
        st.write(chat["answer"])

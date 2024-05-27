from fastapi import FastAPI
from pydantic import BaseModel
import tensorflow as tf
import numpy as np

app = FastAPI()

raw_train_dataset = tf.keras.utils.text_dataset_from_directory(
    "train",
    labels="inferred",
    batch_size=32,
    validation_split=0.2,
    subset="training",
    seed=32
)

model = tf.keras.models.load_model('model.keras')
print(model.summary())

class TextInput(BaseModel):
    text: str


@app.post("/submit-text/")
async def submit_text(text_input: TextInput):
    examples = tf.constant([
        text_input.text,
    ])

    predictions = model.predict(examples)
    keyword = ""
    for i in predictions:
        print(i)
        max_value = np.argmax(i)
        keyword = raw_train_dataset.class_names[max_value]
        print(raw_train_dataset.class_names[max_value])
        print("-------------------------")

    return {"result": keyword}

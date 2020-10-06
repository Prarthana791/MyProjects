#!/usr/bin/env python
# coding: utf-8

# In[1]:


import nltk
nltk.download('punkt')


# In[2]:


import json
f=open('D:\\Study\\Practice projects\\Reverse Dictionary\\wordset.json')
data = json.load(f)
print(type(data))
print(len(data))


# In[31]:


query = input("Enter a sentence to get its 1 word: ")
print(type(query))


# In[32]:


#remove common/stop words
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
stop_words = set(stopwords.words('english'))
words_token = word_tokenize(query)
tokenized_query = [w for w in words_token if not w in stop_words]
print(tokenized_query)


# In[33]:


from numpy.linalg import norm
import numpy as np

similarities = []
i=0
similarity = 0
answer = None
for key,value in data.items():
    vectorA = []
    vectorB = []
    features = list(set(value)|set(tokenized_query))
    for w in features:
        if w in value: vectorA.append(1)
        else: vectorA.append(0)
        if w in tokenized_query: vectorB.append(1)
        else: vectorB.append(0)
    #print('#######'+str(i)+'#########')
    #print(vectorA)
    #print(vectorB)
    cosine_sim = np.dot(vectorA,vectorB)/(norm(vectorA)*norm(vectorB))
    if cosine_sim > similarity:
        answer = key
        similarity = cosine_sim
    similarities.append(cosine_sim)
print('Answer: ',answer)
print('Similarity score: ',similarity)
print(similarities)


# In[ ]:





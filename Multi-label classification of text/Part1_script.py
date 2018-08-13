import pandas as pd
import os
import csv
import numpy as np
from nltk.tokenize import word_tokenize


def IsQues(Infilepath,Outfilepath):
    ques_token = {'when':1,'who':1,'where':1,'why':1,'what':1,'how':1}
    ques = [] #list that stores the code for questions and sentences
    sent = [] #list that stores questions ans sentences
    with open(Infilepath) as csvfile:
        csvdata = csv.reader(csvfile,delimiter = ',')
        for row in csvdata:
            Sentencelength = len(row[0])
            sentence = row[0]
            sentence_tokens = [token.lower() for token in word_tokenize(sentence)]
            sent.append(sentence)

            for word in sentence_tokens:
                if word in ques_token:
                    isquestion = True
                elif word == '?':
                    isquestion = True
                elif word[len(word)-1] == '?':
                    isquestion = True
                else:
                    isquestion = False
    if isquestion == True:
        ques.append(1)
    else:
        ques.append(0)
            #if(sentence[Sentencelength -1] == '?'):
             #   isquestion = True
            #else:
             #   isquestion = False
    
    with open(Outfilepath,"a+") as tsvfile:
        writer = csv.writer(tsvfile,delimiter = '\t',lineterminator='\n')
        for i in range(len(ques)):
            writer.writerow([sent[i],ques[i]])
                        

IsQues('E:\Others\jobs\Learning\Ask\Data.csv','E:\Others\jobs\Learning\Ask\Result.tsv')

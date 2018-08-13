from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.cluster import KMeans
from sklearn.metrics import adjusted_rand_score
import pandas as pd
import csv
import collections

#Funtion from Part-1
data = [] #use to store the questions identifies from the input csv file
def IsQues(Infilepath,Outfilepath):
    ques = []
    sent = []
    with open(Infilepath) as csvfile:
        csvdata = csv.reader(csvfile,delimiter = ',')
        for row in csvdata:
            Sentencelength = len(row[0])
            sentence = row[0]
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
           
IsQues('C:\PS\Periodic_projection\Ask.csv','C:\PS\Periodic_projection\Resultpart1.tsv')


#Part-2 
#Converting question text into vectors
vectorizer = TfidfVectorizer(stop_words='english')
X = vectorizer.fit_transform(data)

#number of clusters into whcich I want to group the input data. Come up with this number by studying the data and running the algorithm multiple times and analyzing the results each time
clusters = 4
model = KMeans(n_clusters=clusters, init='k-means++', max_iter=10, n_init=1)
model.fit(X)

#dictionary that will store label and its respective vector indices; key is label and value is list of indices belongs to that label/cluster   
clustering = collections.defaultdict(list)
for id,label in enumerate(model.labels_):
    clustering[label].append(id)

#writing into csv file the questions and label/clusters to which it belongs
try:
    with open('C:\PS\Periodic_projection\Askresultpart2.csv','w',newline='') as file:
            writer = csv.writer(file, delimiter = ',')
            for label in clustering:
                for sentid in clustering[label]:
                    writer.writerow([data[sentid],label])

 
except:
    print('Exception occured')
  


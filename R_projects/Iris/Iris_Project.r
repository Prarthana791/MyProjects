utils:::menuInstallPkgs()
z.test(black,green,alternative="two.sided',mu=0,sigma.x=21186,sigma.y=21272,conf.level=0.95)
;
z.test(black,green,alternative="two.sided',mu=0,sigma.x=21186,sigma.y=21272,conf.level=0.95)>
z.test(black,green,alternative="two.sided',mu=0,sigma.x=21186,sigma.y=21272,conf.level=0.95)
z.test(help)
help(z.test)
??z.test
q()
q()
q()
mydata=read.table("energytemp.txt",header=T)
ENERGY=mydata$energy
TEMPD=mydata$temp
save.image("E:\\Data Analytics\\HW5\\mydata")
training
dataset=read.csv("IRIS.csv",header=F)
colnames(dataset)=c("Sepal.length","Sepal.width","Petal.lenght","Petal.width","Species")>training_data=createDataPartition(dataset$Species,p=0.80,list=FALSE)
library(caret)
colnames(dataset)=c("Sepal.length","Sepal.width","Petal.lenght","Petal.width","Species")>training_data=createDataPartition(dataset$Species,p=0.80,list=FALSE)
colnames(dataset)=c("Sepal.length","Sepal.width","Petal.lenght","Petal.width","Species")>
colnames(dataset)=c("Sepal.length","Sepal.width","Petal.lenght","Petal.width","Species")
colnames(dataset)=c("Sepal.length","Sepal.width","Petal.lenght","Petal.width","Species")
training_data=createDataPartition(dataset$Species,p=0.80,list=FALSE)
training=dataset[training_data,]
 testing=dataset[-training_data,]
training
testing
dim(dataset)
sapply(dataset, class)
head(dataset)
levels(dataset$Species)
percentage <- prop.table(table(dataset$Species)) * 100
cbind(freq=table(dataset$Species), percentage=percentage)
summary(dataset)
x <- dataset[,1:4]
y <- dataset[,5]
par(mfrow=c(1,4))
  for(i in 1:4) {
  boxplot(x[,i], main=names(iris)[i])
}
plot(y)
featurePlot(x=x,y=y,plot="ellipse")
plot(y)
featurePlot(x=x,y=y,plot="ellipse")
utils:::menuInstallPkgs()
featurePlot(x=x,y=y,plot="ellipse")
featurePlot(x=x,y=y,plot="box")
scales <- list(x=list(relation="free"), y=list(relation="free"))
featurePlot(x=x, y=y, plot="density", scales=scales)
featurePlot(x=x,y=y,plot="box")
scales <- list(x=list(relation="free"), y=list(relation="free"))
featurePlot(x=x, y=y, plot="density", scales=scales)
q()

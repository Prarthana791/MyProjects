library(ggmap)
utils:::menuInstallPkgs()
library(geosphere)
library(ggmap)
library(maps)
library(ggplot2))
library(ggplot2)
world = map_data("world","india")
basemap = ggplot(legend = FALSE) + geom_polygon(data = world, aes(x = long,y = lat, group = group, fill = group)) + theme(legend.position = "none")
basemap = basemap + theme(panel.background = element_rect(fill = "#000000"),panel.grid.major = element_blank(), panel.grid.minor = element_blank())
for(n in 0:350)
{
p <- read.csv("test.csv",skip=n,nrows=1,header = FALSE)
d <- data.frame(lon=p$V1,lat=p$V2)
inter = as.data.frame(gcIntermediate(d, c(88.363895,22.572646),n = 50, addStartEnd = TRUE))
names(inter) = c('lon', 'lat')
basemap = basemap + geom_line(data = inter, aes(x = lon, y = lat), color = "blue")
}
for(n in 0:350)
{
p=read.csv("test.csv",skip=n,nrows=1,header = F)
d=data.frame(lon=p$V1,lat=p$V2)
inter = as.data.frame(gcIntermediate(d, c(88.363895,22.572646),n = 10, addStartEnd = TRUE))
names(inter) = c('lon', 'lat')
basemap = basemap + geom_line(data = inter, aes(x = lon, y = lat), color = "blue")
}
for(n in 0:350)
{
p=read.csv("test.csv",skip=n,nrows=1,header = F, sep=",")
d=data.frame(lon=p$V1,lat=p$V2)
inter = as.data.frame(gcIntermediate(d, c(80.64802,22.30716),n = 10, addStartEnd = TRUE))
names(inter) = c('lon', 'lat')
basemap = basemap + geom_line(data = inter, aes(x = lon, y = lat), color = "blue")
}
for(n in 0:350)
{
p=tryCatch(read.csv("test.csv",skip=n,nrows=1,header = F, sep=","))
d=data.frame(lon=p$V1,lat=p$V2)
inter = as.data.frame(gcIntermediate(d, c(80.64802,22.30716),n = 10, addStartEnd = TRUE))
names(inter) = c('lon', 'lat')
basemap = basemap + geom_line(data = inter, aes(x = lon, y = lat), color = "blue")
}
for(n in 0:10)
{
p=tryCatch(read.csv("test.csv",skip=n,nrows=1,header = F, sep=","), error=function(e) NULL)
d=data.frame(lon=p$V1,lat=p$V2)
inter = as.data.frame(gcIntermediate(d, c(80.64802,22.30716),n = 10, addStartEnd = TRUE))
names(inter) = c('lon', 'lat')
basemap = basemap + geom_line(data = inter, aes(x = lon, y = lat), color = "blue")
}
p
for(n in 0:10)
{
p=read.csv("test.csv",header = F)
d=data.frame(lon=p$V1,lat=p$V2)
inter = as.data.frame(gcIntermediate(d, c(80.64802,22.30716),n = 1, addStartEnd = TRUE))
basemap = basemap + geom_line(data = inter, aes(x = lon, y = lat), color = "blue")
}
for(n in 0:10)
{
p=read.csv("test.csv",header = F)
d=data.frame(lon=p$V1,lat=p$V2)
inter = as.data.frame(gcIntermediate(d, c(88.363895,22.572646),n = 50, addStartEnd = TRUE))
names(inter) = c('lon', 'lat')
basemap = basemap + geom_line(data = inter, aes(x = lon, y = lat), color = "blue")
}
p
d
inter
basemap
q()

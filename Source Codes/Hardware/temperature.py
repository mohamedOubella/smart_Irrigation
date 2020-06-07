import time
from datetime import datetime
import json
import os
dictionaire={"id":1 # c'est un dictionaire qui sert a ecrire dans le ficier json
    }
tempture=-273,15 # on initliase la variable de temperature 
def lireFichier (emplacement) : # cette fonction permet de lire la temperature
    fichTemp = open(emplacement)
     contenu = fichTemp.read() # on lit la temperature
    fichTemp.close() # on ferme le fichier
    return contenu # on retourne la temperature
def recupTemp (contenuFich) : # cette fonction permet de recuprer la temperature( sous format nombre)
    secondeLigne = contenuFich.split("\n")[1] 
    temperatureData = secondeLigne.split(" ")[9]
    temperature = float(temperatureData[2:])
    temperature = temperature / 1000
    return temperature # on retourne la temperature
def read_period(): # on lit a partir de fichier periode
	with open('periode.json','r') as JS:
		data=json.load(JS)
		JS.close()
	return data["TEMPERATUREPERIODE"]
while True :
    date=datetime.now().strftime('%Y_%m_%d__%H_%M_%S') # on recupere le temps pour que le nom de fichier soit unique
    contenuFich = lireFichier("/sys/bus/w1/devices/28-000006f1da59/w1_slave") # on lit le fichier
    tempture = recupTemp(contenuFich) # on recupere la temperature
    dictionaire.update(valeur=tempture) # on met la nouvelle valeur de temperature dans le fichier
    with open(date+".json","w+") as json_file:
	    json.dump(dictionaire,json_file) #on ecrit dans le fichier json
	    json_file.close() # on ferme le fichier
    os.system("python3 ./sftp.py ./"+date+".json") # on execute la commande de sftp et on passe le nom de ce fichier 
    os.remove(date+".json")
    time.sleep(read_period())  # on attente la periode

 

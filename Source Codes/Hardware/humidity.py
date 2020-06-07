import RPi.GPIO as GPIO
from time import sleep
import json
import sys
import os

channel =16#l'entree 16 de raspberry
json_sens={"id":2} # c'est pour ecrire dans le fichier 
GPIO.setmode(GPIO.BOARD) # c'est pour configurer la raspberry
GPIO.setup(channel,GPIO.IN) #pour que le port soit en mode entree
name="humidity.json" # le nom de fichier json 
def write_json(state):  # la fonction qui sert a ecrire dans le fichier json
	json_sens.update(valeur = state) # pour modfier la valeur d'etat dans le fichier
	with open(name , 'w') as JS:
		json.dump(json_sens,JS)
		JS.close()
def read_period(): # pour lire la periode a partir de fichier PERIODE
	with open('periode.json','r') as JS: 
		data=json.load(JS)  # on lit la periode
		JS.close() #on ferme le fichier
	return data["HUMIDITEPERIODE"]	 	
while True:# fonctionement de capteur d'humidite
	if GPIO.input(channel): 
		write_json('false')
	else:
		write_json('true')
	os.system("python3 sftp.py ./"+name) # pour envoyer le fichier au serveur pour etre traiter , on envoi le nom dans les argument de commande
	sleep(read_period())# on attend la periode

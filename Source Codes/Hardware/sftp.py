#c'est le fichier qui permet l'envoi de fichier au serveur
import pysftp
import sys
Host = "192.168.43.15" # l'ip de serveur
User = "skyzelda" # username pour se connecter au serveur
Pass = "24524" # le mot de passe de serveur
cnopts = pysftp.CnOpts() # pour intialser la communication  
cnopts.hostkeys = None # pour ressourdre un probleme de cle
with pysftp.Connection(host=Host,username=User,password=Pass,cnopts=cnopts) as sftp: #on se connecte
	print("Connection established") # on envoi un message qui indique que la connection est faite
	localFilePath=sys.argv[1]  # on recupere le nom de fichier qui passer a partir de d'argument d'execution
	remoteFilePath="C:/Users/skyzelda/pi_stuff/project/"+sys.argv[1]+".json" # le emplacement dans le serveur ou le fichier va etre enregistrer c'est le dossier partage
	sftp.put(localFilePath,remoteFilePath) # on envoi le fichier
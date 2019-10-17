#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <netdb.h>
#include <errno.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <getopt.h>

#define BUFSIZE 8019

#define USAGE                                                                 \
"usage:\n"                                                                    \
"  echoserver [options]\n"                                                    \
"options:\n"                                                                  \
"  -p                  Port (Default: 19801)\n"                                \
"  -m                  Maximum pending connections (default: 1)\n"            \
"  -h                  Show this help message\n"                              \

/* OPTIONS DESCRIPTOR ====================================================== */
static struct option gLongOptions[] = {
  {"port",          required_argument,      NULL,           'p'},
  {"maxnpending",   required_argument,      NULL,           'm'},
  {"help",          no_argument,            NULL,           'h'},
  {NULL,            0,                      NULL,             0}
};


int main(int argc, char **argv) {
  int option_char;
  int portno = 19801; /* port to listen on */
  int maxnpending = 1;

  // Parse and set command line arguments
  while ((option_char = getopt_long(argc, argv, "p:m:hx", gLongOptions, NULL)) != -1) {
   switch (option_char) {
      case 'p': // listen-port
        portno = atoi(optarg);
        break;
      default:
        fprintf(stderr, "%s ", USAGE);
        exit(1);
      case 'm': // server
        maxnpending = atoi(optarg);
        break;
      case 'h': // help
        fprintf(stdout, "%s ", USAGE);
        exit(0);
        break;
    }
  }

    setbuf(stdout, NULL); // disable buffering

    if ((portno < 1025) || (portno > 65535)) {
        fprintf(stderr, "%s @ %d: invalid port number (%d)\n", __FILE__, __LINE__, portno);
        exit(1);
    }
    if (maxnpending < 1) {
        fprintf(stderr, "%s @ %d: invalid pending count (%d)\n", __FILE__, __LINE__, maxnpending);
        exit(1);
    }


  /* Socket Code Here */

  int serverSocket, bindSocket, newSocket;
	char buffer[512];

	 // Define the server address
	struct  sockaddr_in server_address;

	server_address.sin_family =AF_INET;
	server_address.sin_port = htons(7000);
	server_address.sin_addr.s_addr = INADDR_ANY;


      serverSocket=socket(AF_INET, SOCK_STREAM, 0);

      if (serverSocket<0)
      {
      	perror(" sever opening failure");
      	exit(1);
      }

	bindSocket= bind(serverSocket, (struct sockaddr*)&server_address,sizeof(server_address));
       if (bindSocket<0)
       {
       	   perror("Server failed to bind successfully");
       }

       	listen(serverSocket, 5);
       	while(1){
        newSocket=accept(serverSocket, NULL, NULL);

        if (newSocket<0)
        {
          perror("failure creating the socket");
        }

        char  message[256]="Connection successful";

	    send(newSocket,message, sizeof(message),0);

	    recv(newSocket,buffer,sizeof(buffer),0);
	    printf("%s \n",buffer);

	    send(newSocket,buffer, sizeof(buffer),0);
	}

	    // close(serverSocket);
      close(serverSocket);


	return 0;

}

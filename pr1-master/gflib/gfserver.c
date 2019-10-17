
#include "gfserver-student.h"

/*
 * Modify this file to implement the interface specified in
 * gfserver.h.
 */



void gfs_abort(gfcontext_t **ctx){
   exit(0);
}

gfserver_t* gfserver_create(){

    // Define the server address
    struct  sockaddr_in server_address;

  server_address.sin_family =AF_INET;
	server_address.sin_addr.s_addr = INADDR_ANY;
   gfserver_set_port();

    return server_address;
}

ssize_t gfs_send(gfcontext_t **ctx, const void *data, size_t len){
  send(ctx,data, len0);
}

ssize_t gfs_sendheader(gfcontext_t **ctx, gfstatus_t status, size_t file_len){
    return -1;
}

void gfserver_serve(gfserver_t **gfs){
  int server_socket=socket(AF_INET, SOCK_STREAM, 0);


}

void gfserver_set_handlerarg(gfserver_t **gfs, void* arg){

   bind(server_socket, (struct sockaddr*)&server_address,sizeof(server_address));

   listen(server_socket, 5);

   int client_socket;
   client_socket=accept(server_socket, NULL, NULL);

   send(client_socket,message, sizeof(message),0);
}

void gfserver_set_handler(gfserver_t **gfs, gfh_error_t (*handler)(gfcontext_t **, const char *, void*)){

}

void gfserver_set_maxpending(gfserver_t **gfs, int max_npending){
  int server_socket=socket(AF_INET, SOCK_STREAM, 0);

    // Define the server address
  struct  sockaddr_in server_address;

  server_address.sin_family =AF_INET;
  server_address.sin_port = htons(9002);
  server_address.sin_addr.s_addr = INADDR_ANY;

  bind(server_socket, (struct sockaddr*)&server_address,sizeof(server_address));

  listen(server_socket, 5);
}

void gfserver_set_port(gfserver_t **gfs, unsigned short port){
  gfs.sin_port = htons(port);
}


#include "gfclient-student.h"

// optional function for cleaup processing.
void gfc_cleanup(gfcrequest_t **gfr){

}

gfcrequest_t *gfc_create(){
  int network_socket=socket(AF_INET,SOCK_STREAM, 0);


  // Specifyng the socket address
  struct sockaddr_in server_address;

  server_address.sin_family =AF_INET;
  server_address.sin_port =htons(9002);
  server_address.sin_addr.s_addr=INADDR_ANY;

    return (gfcrequest_t *)NULL;

}

size_t gfc_get_bytesreceived(gfcrequest_t **gfr){
    // not yet implemented
    return recv(gfr, &message, sizeof(message),0);
}

size_t gfc_get_filelen(gfcrequest_t **gfr){

    return gfr.length;
}

gfstatus_t gfc_get_status(gfcrequest_t **gfr){
  int con_status= connect(gfr, (struct sockaddr *) &server_address, sizeof(server_address));
    return con_status;
}

void gfc_global_init(){
}

void gfc_global_cleanup(){
}

int gfc_perform(gfcrequest_t **gfr){
    // currently not implemented.  You fill this part in.
    return -1;
}

void gfc_set_headerarg(gfcrequest_t **gfr, void *headerarg){

}

void gfc_set_headerfunc(gfcrequest_t **gfr, void (*headerfunc)(void*, size_t, void *)){

}

void gfc_set_path(gfcrequest_t **gfr, const char* path){

}

void gfc_set_port(gfcrequest_t **gfr, unsigned short port){

}

void gfc_set_server(gfcrequest_t **gfr, const char* server){

}

void gfc_set_writearg(gfcrequest_t **gfr, void *writearg){

}

void gfc_set_writefunc(gfcrequest_t **gfr, void (*writefunc)(void*, size_t, void *)){

}

const char* gfc_strstatus(gfstatus_t status){
    const char *strstatus = NULL;

    switch (status)
    {
        default: {
            strstatus = "UNKNOWN";
        }
        break;

        case GF_INVALID: {
            strstatus = "INVALID";
        }
        break;

        case GF_FILE_NOT_FOUND: {
            strstatus = "FILE_NOT_FOUND";
        }
        break;

        case GF_ERROR: {
            strstatus = "ERROR";
        }
        break;

        case GF_OK: {
            strstatus = "OK";
        }
        break;

    }

    return strstatus;
}

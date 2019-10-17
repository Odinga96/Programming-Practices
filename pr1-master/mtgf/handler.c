#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <errno.h>
#include <unistd.h>

#include "gfserver.h"
#include "gfserver-student.h"
#include "content.h"

#define BUFFER_SIZE 8019

//
//  The purpose of this function is to handle a get request
//
//  The ctx is a pointer to the "context" operation and it contains connection state
//  The path is the path being retrieved
//  The arg allows the registration of context that is passed into this routine.
//  Note: you don't need to use arg. The test code uses it in some cases, but
//        not in others.
//
gfh_error_t gfs_handler(gfcontext_t **ctx, const char *path, void* arg){
	return gfh_failure;
}


from django.db import models
from django.contrib.auth.models import User
from django.db.models.signals import post_save


class UserProfile(models.Model):
	"""Model declaring the fields for userprofile object.
    """
	user = models.OneToOneField(User, on_delete=models.CASCADE)
	address = models.CharField(max_length=100, default='')
	city = models.CharField(max_length=100, default='')
	phone_number = models.IntegerField(default=0)
	image = models.ImageField(upload_to='profile_image' , blank=True)
	first_name =  models.CharField(max_length=100, default='')
	last_name =  models.CharField(max_length=100, default='')

	def __str__(self):
 		return self.user.username

def create_profile(sender,**kwargs ):
	"""Function creating automatically the UserProfile when a new User is created.

    Arg: sender that send the signal in this case current user
    """
	if kwargs['created']:
		user_profile=UserProfile.objects.create(user=kwargs['instance'])

post_save.connect(create_profile,sender=User)
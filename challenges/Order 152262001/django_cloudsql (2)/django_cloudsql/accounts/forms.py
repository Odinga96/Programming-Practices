from django import forms
from django.contrib.auth.models import User
from django.contrib.auth.forms import UserCreationForm, UserChangeForm
from django.forms import ModelForm
from accounts.models import UserProfile


class EditProfileForm(ModelForm):
    """Class extenfing ModelForm to include the User object needed.
    """
    class Meta:
        model = User
        fields = (
                 'email',
                 'first_name',
                 'last_name'
                )


class ProfileForm(ModelForm):
    """Class extenfing ModelForm to include the UserProfile object needed.
    """
    class Meta:
        model = UserProfile
        fields = ('city', 'address', 'phone_number', 'image') 


class RegisterForm(UserCreationForm):
    """Class extenfing UserCreationForm to include the fields needed.
    """
    username = forms.CharField(label="Your Username")
    password1 = forms.CharField(label="Your Password")
    password2 = forms.CharField(label="Repeat Your Password")
    email = forms.EmailField(label = "Email Address")
    first_name = forms.CharField(label = "Name")
    last_name = forms.CharField(label = "Surname")
 
    class Meta:
        model = User
        fields = ("first_name", "last_name", "email", "username", "password1", "password2", )
 
    def save(self, commit=True):
        """When commit is done from the UI, save the data from the form, and include field to User that are not standard.
            """
        user = super(RegisterForm, self).save(commit=False)
        user.email = self.cleaned_data["email"]
        user.first_name = self.cleaned_data["first_name"]
        user.last_name = self.cleaned_data["last_name"]
 
        if commit:
            user.save()
        return user
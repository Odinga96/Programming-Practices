from django import forms
from django.contrib.auth.models import User

class ContactForm(forms.Form):
	"""Class extending Form with the required fields.
    """
	contact_name = forms.CharField(required=True, label="Name")
	contact_email = forms.EmailField(required=True, label="Email")
	content = forms.CharField(
        required=True,
        widget=forms.Textarea,
        label="Message"
    )

from django.contrib.auth.decorators import login_required
from django.shortcuts import render, redirect
from datetime import datetime
from django.contrib.auth.forms import UserCreationForm
from accounts.forms import EditProfileForm, ProfileForm, RegisterForm
from django.contrib.auth import update_session_auth_hash
from django.contrib.auth.models import User
from django.shortcuts import get_object_or_404
from .models import UserProfile
from main.models import Order

def signup(request):
    """Function collecting the data needed to send to the UI for Signup page.
    """
    if request.method == 'POST':
        form = RegisterForm(request.POST)
        if form.is_valid():
            new_user = form.save()
        return redirect("confirmation")
    else:
        form = RegisterForm()
    return render(request, 'signup.html', { 'form': form })

def confirmation(request):
    """Function collecting the data needed to send to the UI for Confirmation page.
    """
    context = {"about_page": "active"} 
    return render(request, 'confirmation.html', context)

@login_required
def view_profile(request):
    """Function collecting the data needed to send to the UI for View_profile page, require a user to be logged in.
    """
    orders = Order.objects.filter(user=request.user)
    user = get_object_or_404(User, username=request.user)
    profile = get_object_or_404(UserProfile, user=request.user)
    return render(request, 'view_profile.html', {"user":user, "profile": profile, "orders": orders})

@login_required
def edit_profile(request):
    """Function collecting the data needed to send to the UI for View_profile page, require a user to be logged in.
    """
    if request.method == 'POST':
        form = EditProfileForm(request.POST, instance=request.user)
        profile_form = ProfileForm(request.POST, request.FILES, instance=request.user.userprofile)  

        if form.is_valid() and profile_form.is_valid():
            user_form = form.save()
            custom_form = profile_form.save(False)
            custom_form.user = user_form
            custom_form.save()
        return redirect('/')
    else:
        profile_form = ProfileForm(instance=request.user.userprofile)
        form = EditProfileForm(instance=request.user)
        args = {}
        args['form'] = form
        args['profile_form'] = profile_form
        return render(request, 'edit_profile.html', args)
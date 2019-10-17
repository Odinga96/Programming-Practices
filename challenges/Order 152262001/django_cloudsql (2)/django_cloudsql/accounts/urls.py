from django.urls import path, include
from django.conf.urls import url
from . import views

urlpatterns = [
    path('accounts/', include('django.contrib.auth.urls')),
    path('signup/', views.signup, name='signup'),
    path('view_profile/', views.view_profile, name='view_profile'),
    path('edit_profile/', views.edit_profile, name ='edit_profile'),
    path('confirmation/', views.confirmation, name='confirmation')
]
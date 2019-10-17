import os
from django.core.mail import BadHeaderError, EmailMessage
from django.contrib.auth.decorators import user_passes_test
from django.conf import settings
from django.http import HttpResponse, Http404
from django.shortcuts import render, redirect
from django.urls import reverse_lazy
from .models import Article, Order, Item
from accounts.models import UserProfile
from .forms import ContactForm
from .query import get_query

def index(request):
    """Function collecting the data needed to send to the UI for index page.
    """
    context = {"home_page": "active"}
    try:
        p = Article.objects.order_by('pub_date')[0:2]
    except Article.DoesNotExist:
        raise Http404("Poll does not exist")
    files = os.listdir(os.path.join(settings.STATIC_ROOT,"carousel"))
    return render(request, 'index.html', {'files' : files, 'articles': p}, context)

def about(request):
    """Function collecting the data needed to send to the UI for about page.
    """
    context = {"about_page": "active"}
    return render(request, 'about.html', context)

def contact(request):
    """Function collecting the data needed to send to the UI for contact page.
    """
    if request.method == 'GET':
        form = ContactForm()
    else:
        form = ContactForm(request.POST)
        if form.is_valid():
            contact_name = form.cleaned_data['contact_name']
            contact_email = form.cleaned_data['contact_email']
            content = form.cleaned_data['content']
            try:
                email = EmailMessage(contact_name,
                                    content,
                                    contact_email,
                                    ['youremail@gmail.com'], 
                                     reply_to=[contact_email],
                                   )
                email.send()
            except BadHeaderError:
                return HttpResponse('Invalid header found.')
            return redirect('./thanks/')
    context = {"contact_page": "active"} 
    return render(request, 'contact.html', {'form': form}, context)


def thanks(request):
    """Function collecting the data needed to send to the UI for thanks page.
    """
    return render(request, 'thanks.html')

@user_passes_test(lambda u:u.is_staff, login_url=reverse_lazy('login'))
def all_orders(request):
    """Function collecting the data needed to send to the UI for detail page.
    """
    obj = Order.objects.all()
    return render(request, 'detail.html', {'obj': obj})

@user_passes_test(lambda u:u.is_staff, login_url=reverse_lazy('login'))
def all_items(request):
    """Function collecting the data needed to send to the UI for detail page.
    """
    obj = Item.objects.all()
    return render(request, 'detail.html', {'obj': obj})

@user_passes_test(lambda u:u.is_staff, login_url=reverse_lazy('login'))
def all_users(request):
    """Function collecting the data needed to send to the UI for detail page.
    """
    obj = UserProfile.objects.all()
    return render(request, 'detail.html', {'obj': obj})

def search(request):
    """Function collecting the data needed to send to the UI for search page.
    """
    query_string = ''
    found_entries = None
    if ('q' in request.GET) and request.GET['q'].strip():
        query_string = request.GET['q']
        entry_query = get_query(query_string, ['title', 'description',])
        items = Item.objects.filter(entry_query)
        return render(request, 'search.html', { 'query_string': query_string, 'items': items })
    else:
        return render(request, 'search.html', { 'query_string': 'Null', 'found_entries': 'Enter a search term' })
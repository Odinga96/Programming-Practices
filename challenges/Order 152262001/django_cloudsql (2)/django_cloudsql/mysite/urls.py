from django.conf import settings
from django.conf.urls import include, url
from django.contrib import admin
from django.contrib.staticfiles.urls import staticfiles_urlpatterns
from django.conf.urls.static import static

urlpatterns = [url(r'^admin/', admin.site.urls),
               url('', include('main.urls'))]+ static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)


# This enables static files to be served from the Gunicorn server
# In Production, serve static files from Google Cloud Storage or an alternative
# CDN
if settings.DEBUG:
    urlpatterns += staticfiles_urlpatterns()

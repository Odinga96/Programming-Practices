# Generated by Django 2.1.9 on 2019-08-19 21:35

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('accounts', '0003_userprofile_image'),
    ]

    operations = [
        migrations.RenameField(
            model_name='userprofile',
            old_name='description',
            new_name='address',
        ),
        migrations.RenameField(
            model_name='userprofile',
            old_name='phoneNumber',
            new_name='phone_number',
        ),
        migrations.RemoveField(
            model_name='userprofile',
            name='website',
        ),
    ]
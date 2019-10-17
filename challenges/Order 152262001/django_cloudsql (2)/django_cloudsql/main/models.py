# Copyright 2015 Google Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

from uuid import uuid4
from django.db import models
from django.contrib.auth.models import User


class Article(models.Model):
	"""
    Model declaring the fileds for article object.
    """
	title = models.CharField(max_length=100)
	text = models.CharField(max_length=5000)
	pub_date = models.DateTimeField('date published')

	def __str__(self):
		return self.title


class Order(models.Model):
	"""
    Model declaring the fields for order object.
    """
	user = models.ForeignKey(
        User,
        on_delete=models.CASCADE,)
	mykey = models.CharField(max_length=100, unique=True, default=uuid4, editable=False)
	date_order = models.DateTimeField('date paid')
	items  = models.ManyToManyField("Item", related_name="order_items", blank=True)


class Item(models.Model):
	"""
    Model declaring the fields for item object.
    """
	title = models.CharField(max_length=50)
	description = models.CharField(max_length=200)

	def __str__(self):
		return self.title

	def __unicode__(self):
		return self.title
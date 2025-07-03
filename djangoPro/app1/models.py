from django.db import models
from django.utils import timezone
from django.contrib.auth.models import User

# Create your models here.
class Allmodel(models.Model):
    APP_TYPE = [
        ('ht','height'),
        ('wt','weight')
    ]
    name = models.CharField(max_length=100)
    image = models.ImageField(upload_to='app1/')
    date = models.DateTimeField(default=timezone.now)
    type = models.CharField(max_length=2,choices=APP_TYPE)
    description = models.TextField(default='')



    def __str__(self):
        return self.name
  
# one to many
class Allrev(models.Model):
    rev = models.ForeignKey(Allmodel,on_delete=models.CASCADE, related_name='rev')
    user = models.ForeignKey(User,on_delete=models.CASCADE)
    rating = models.IntegerField()
    comment = models.TextField()
    date_added = models.DateTimeField(default=timezone.now)

    def __str__(self):
        return f'{self.user.username} review for {self.Allmodel.name}'
    

# many to many
class Store(models.Model):
    name = models.CharField(max_length=100)
    location = models.CharField(max_length=100)
    all_var = models.ManyToManyField(Allmodel,related_name='stores')

    def __str__(self):
        return self.name
    

# one to one
class Allcer(models.Model):
    all = models.OneToOneField(Allmodel,on_delete=models.CASCADE,related_name='certi')
    cert_no = models.CharField(max_length=100)
    issue = models.DateTimeField(default=timezone.now)
    valid = models.DateTimeField()

    def __str__(self):
        return f'Certi for {self.name.all}'
    

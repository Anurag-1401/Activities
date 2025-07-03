
from django.urls import path
from . import views

urlpatterns = [
    path('',views.all,name='all'),
    path('<int:all_id>/',views.all_des,name='all_des'),
    path('all_stores/',views.all_store,name='store'),

]

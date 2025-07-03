from django.shortcuts import render,get_object_or_404
from .models import Allmodel , Store
from .forms import AV

# Create your views here.
def all(request):
    apll = Allmodel.objects.all()
    return render(request,'app1/all.html',{'ap':apll})

def all_des(request,all_id):
    allin  = get_object_or_404(Allmodel,pk=all_id)
    return render(request,'app1/all_det.html',{'det':allin})

def all_store(request):
    stores = None
    if request.method == 'POST':
        form = AV(request.POST)
        if form.is_valid():
            all_vt =  form.cleaned_data['all_vry']
            stores = Store.objects.filter(all_var = all_vt)
    else:
        form = AV()

    return render(request,'app1/all_stores.html',{'stores':stores,'form':form})
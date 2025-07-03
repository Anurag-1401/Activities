from django import forms
from .models import Allmodel

class AV(forms.Form):
    all_vry = forms.ModelChoiceField(queryset=Allmodel.objects.all()
    ,label="select")
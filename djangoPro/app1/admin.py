from django.contrib import admin
from .models import Allmodel,Allrev,Allcer,Store

# Register your models here.
class AllrevInLine(admin.TabularInline):
    model = Allrev
    extra = 2

class AllmodelAdmin(admin.ModelAdmin):
    list_display = ('name','type','date',)
    inlines = [AllrevInLine]

class StoreAdmin(admin.ModelAdmin):
    list_display = ('name','location',)
    filter_horizontal = ('all_var',)

class AllcerADmin(admin.ModelAdmin):
    list_display = ('all','cert_no',)


# admin.site.register(Allmodel,AllmodelAdmin)
admin.site.register(Store,StoreAdmin)
admin.site.register(Allcer,AllcerADmin)
admin.site.register(Allmodel,AllmodelAdmin)


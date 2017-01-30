

Ext.define('SGCEscalada.view.media.GaleriaImagenes', {
    require: [
        'Ext.data.*',
        'Ext.util.*',
        'Ext.view.View',
        'Ext.ux.DataView.Animated',       
        'Ext.XTemplate',
        'Ext.panel.Panel',
        'Ext.toolbar.*',
        'Ext.slider.Multi'        
    ],
    extend: 'Ext.view.View',
    alias: 'widget.galeriaImagenes',
    store: Ext.create('SGCEscalada.store.media.GaleriaImagenesStore'),
    tpl: Ext.create('Ext.XTemplate',
            '<tpl for=".">',
             '<div class="phone">',
             "<img width='74' height='74' src='img/{src}' onclick='window.location.href =\"img/{src}\"'/>",
              '<strong>{name}</strong>',             
             '</div>',
             '</tpl>'
            ),
//
//    plugins: [
//        Ext.create('Ext.ux.DataView.Animated', {
//            duration: 10,
//            idProperty: 'id'
//        })
//    ],
    id: 'phones',
    itemSelector: 'div.phone',    
    overItemCls: 'phone-hover',    
    emptyText:"No existen imágenes para mostrar",
    multiSelect: true,
    autoScroll: true
    
});
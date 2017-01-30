Ext.define('SGCEscalada.view.media.GaleriaVideos', {
    require:[
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
    alias: 'widget.galeriaVideos',
    //deferInitialRefresh: true,
    store: Ext.create('SGCEscalada.store.media.GaleriaVideosStore')/*Ext.create('Ext.data.ArrayStore', {
        fields: [
            {name: 'hasEmail', type: 'bool'},
            {name: 'hasCamera', type: 'bool'},
            {name: 'id', type: 'int'},
            'name',
            {name: 'price', type: 'int'},
            'screen',
            'camera',
            'color',
            'type',
            {name: 'src'},
            {name: 'reviews', type: 'int'},
            {name: 'screen-size', type: 'int'}
        ],
        sortInfo: {
            field: 'name',
            direction: 'ASC'
        },
        data: [
            [true, false, 1, "dance_fever", 54, "240 x 320 pixels", "2 Megapixel", "Pink", "Slider", 359, 2.400000]
        ]
    })*/,
    tpl: Ext.create('Ext.XTemplate',
        '<tpl for=".">',
        '<div style="margin-bottom: 10px;" class="thumb-wrap">',
       // "<a width='350' height='350' data-media-action='play' href='img/{src}'>{src}</a>",
        "<video width = '300px' controls = 'controls' preload = 'none'>",
        "<source type='video/webm' src='img/{src}'>{src}</source>",
        "</video>",
        '</div>',
        '</tpl>'

        /*'<tpl for=".">',
        '<div class="phone">',
        '<img width="100" height="100" src="{[values.src]}" />',
       // '<strong>{name}</strong>',
       // '<span>{price:usMoney} ({reviews} Review{[values.reviews == 1 ? "" : "s"]})</span>',
        '</div>',
        '</tpl>'*/
    ),

    /*plugins: [
        Ext.create('Ext.ux.DataView.Animated', {
            duration: 550,
            idProperty: 'id'
        })
    ],*/
    id: 'phones',

    itemSelector: 'div.phone',
    overItemCls: 'phone-hover',
    multiSelect: true,
    autoScroll: true
});
Ext.define('SGCEscalada.view.media.MenuMedia',{
    extend: 'Ext.Button',
    alias:'widget.menuMedia',
    text: 'Galer&iacute;a',

    initComponent:function(){
        var me = this;
        
        me.menu = Ext.create('Ext.menu.Menu', {
            border:false,
            items:[
                {
                    text: 'Videos',
                    action: 'menuMediaVideos'
                },
                {
                    text: 'Im&aacute;genes',
                    action: 'menuMediaImagenes'
                }
            ]
        });
        me.callParent(arguments);
    }
});
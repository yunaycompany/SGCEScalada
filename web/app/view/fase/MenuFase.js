Ext.define('SGCEscalada.view.fase.MenuFase',{
    extend: 'Ext.Button',
    alias:'widget.menuFase',
    text: 'Fase',

    initComponent:function(){
        var me = this;
        me.menu = Ext.create('Ext.menu.Menu', {
            border:false,
            items:[
                {
                    text: 'Fases',
                    action: 'menuFaseDificultad'
                }
            ]
        });
        me.callParent(arguments);
    }
});
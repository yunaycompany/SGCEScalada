Ext.define('SGCEscalada.view.Secundario', {
    extend: 'Ext.panel.Panel',
    title: 'Competencias',
    alias: 'widget.secundario',
    region: 'west',
    border: false,
    collapsible: true,
    animCollapse: true,
    margins: '2 3 2 1',
    layout: 'fit',
    width: '20%',

    initComponent: function () {
        var me = this;

        me.dtvCompetencias = Ext.create('Ext.view.View',{
            trackOver: true,
            store: Ext.create('SGCEscalada.store.competencia.CompetenciasStore', {
                autoLoad: true
            }),
            cls: 'feed-list',
            itemSelector: '.feed-list-item',
            overItemCls: 'feed-list-item-hover',
            tpl: '<tpl for="."><div class="feed-list-item">{nombre}</div></tpl>',
            listeners: {
                //selectionchange: this.onSelectionChange,
                scope: this
            }
        });

        me.items =[me.dtvCompetencias];
        /*me.tbar = [
            Ext.widget('menuDeportista'),
            Ext.widget('menuCanton'),
            Ext.widget('menuFederacion'),
            Ext.widget('menuLugar'),
            Ext.widget('menuParroquia'),
            Ext.widget('menuProvincia'),
            Ext.widget('menuUsuario'),
            Ext.widget('menuFase'),
            Ext.widget('menuCompetencia')
        ];*/
        me.callParent(arguments);
    }
});
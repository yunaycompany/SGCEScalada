Ext.define('SGCEscalada.view.media.FrmMedia',{
    extend: 'Ext.form.Panel',
    alias: 'widget.frmMedia',
    border:0,
    layout:'fit',
    

    initComponent:function(){
        var me = this;



        if(me.videos){
            me.items = [Ext.widget('galeriaVideos', {
                margin: 5,
                padding: 5
            })];
        }
        else{
            me.items = [Ext.widget('galeriaImagenes', {
                margin: 5,
                padding: 5
            })];
        }

        if (!anonimo && !jurado) {
            if (me.videos) {
                me.btnSubirVideo = Ext.create('Ext.button.Button', {
                    text: 'Subir video',
                    action: 'subirVideo'
                });
                me.buttons = [me.btnSubirVideo];
            }
            else {
                me.btnSubirImagen = Ext.create('Ext.button.Button', {
                    text: 'Subir imagen',
                    action: 'subirImagen'
                });
                me.buttons = [me.btnSubirImagen];
            }

        }
        me.callParent(arguments);
    }
});
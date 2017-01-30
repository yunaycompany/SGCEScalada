Ext.define('SGCEscalada.controller.PrincipalController', {
    extend: 'Ext.app.Controller',
    views: [
        'Principal',
        'canton.AdicionarCanton',
        'loggin.Loggin',
        'media.GaleriaImagenes',
        'media.GaleriaVideos',
        'media.FrmMedia',
        'media.MenuMedia',
        'media.CargarImagen',
        'media.CargarVideo',
        'media.WndSubirImagen',
        'media.WndSubirVideo'
    ],
    stores: ['media.GaleriaImagenesStore', 'media.GaleriaVideosStore'],
    refs: [
        {
            ref: 'principal',
            selector: 'principal'
        },
        {
            ref: 'galeriaImagenes',
            selector: 'galeriaImagenes'
        },
        {
            ref: 'galeriaVideos',
            selector: 'galeriaVideos'
        },
        {
            ref: 'loggin',
            selector: 'loggin'
        },
        {
            ref: 'menuMedia',
            selector: 'menuMedia'
        },
        {
            ref: 'frmMedia',
            selector: 'frmMedia'
        },
        {
            ref: 'cbxCompetencias',
            selector: 'cbxCompetencias'
        },
        {
            ref: 'cbxCompetencias',
            selector: 'cbxCompetencias'
        },
        {
            ref: 'wndSubirVideo',
            selector: 'wndSubirVideo'
        },
        {
            ref: 'wndSubirImagen',
            selector: 'wndSubirImagen'
        }
    ],
    init: function() {
        this.control({
            'principal button[action=aceptar]': {
                render: this.onClickAceptarAdicionarCanton
            },
            'loggin button[action=aceptarLoggin]': {
                click: this.onClickAceptarLoggin
            },
            'loggin': {
                render: this.onRenderLoggin
            },
            'menuMedia component[action=menuMediaVideos]': {
                click: this.onClickMenuMediaVideos
            },
            'menuMedia component[action=menuMediaImagenes]': {
                click: this.onClickMenuMediaImagenes
            },
            'frmMedia component[action=subirImagen]': {
                click: this.onClickSubirImagen
            },
            'frmMedia component[action=subirVideo]': {
                click: this.onClickSubirVideo
            },
            'wndSubirImagen component[action=aceptarCargarImagenSubir]': {
                click: this.onClickAceptarCargarImagenSubir
            },
            'wndSubirVideo component[action=aceptarCargarVideoSubir]': {
                click: this.onClickAceptarCargarVideoSubir
            },
            'wndSubirImagen component[action=cancelarCargarImagenSubir]': {
                click: this.onClickCancelarCargarImagenSubir
            },
            'wndSubirVideo component[action=cancelarCargarVideoSubir]': {
                click: this.onClickCancelarCargarImagenSubir
            },
            'galeriaImagenes': {
                render: this.onRenderGaleriaImagenes
            }
        });
    },
    onClickMenuMediaVideos: function() {
        var me = this;
        if (me.getPrincipal().items.items.length > 0) {
            me.getPrincipal().remove(me.getPrincipal().items.items[0]);
        }
        me.getPrincipal().add(Ext.widget('frmMedia', {videos: true}));
    },
    onClickMenuMediaImagenes: function() {
        var me = this;
        if (me.getPrincipal().items.items.length > 0) {
            me.getPrincipal().remove(me.getPrincipal().items.items[0]);
        }
        me.getPrincipal().add(Ext.widget('frmMedia'));
    },
    onClickAceptarAdicionarCanton: function() {
        Ext.widget('adicionarDeportista');
    },
    onClickAceptarLoggin: function(th) {
        var /*me  = this,*/
                frm = th.up('form').getForm()/*,
                 wnd = th.up('window')*/;

        frm.submit({
            url: 'j_spring_security_check',
            method: 'POST',
            success: function(form, action) {
                Ext.Msg.alert('Success', action);
//                console.log(action);
            },
            failure: function(form, action) {
//                console.log(action.result);
                //var msg = action.result.msg;
                Ext.Msg.alert('Error', 'failed');
            }
        });

        // wnd.destroy();
    },
    onRenderLoggin: function(th) {
        th.down('form').getForm().reset();
    },
    onClickSubirImagen: function() {
        Ext.widget('wndSubirImagen');
    },
    onClickSubirVideo: function() {
        Ext.widget('wndSubirVideo');
    },
    onClickAceptarCargarImagenSubir: function(th) {
        var me = this,
                frm = th.up('form').getForm();
        var nombre = me.getWndSubirImagen().nombre.value;
        var fecha = me.getWndSubirImagen().fecha.value;
      
        frm.submit({
            url: 'subirImagen.htm',
            method: 'POST',
            params: {
//               nombre: nombre 
            },
            success: function(form, action) {
                if (action.result.success) {
                    Ext.Ajax.request({
                        url: 'cambiarParamsImg.htm',
                        method: 'POST',
                        params: {
                            nombre: nombre,
                            fecha: fecha,
                            idmedia: action.result.idmedia ? action.result.idmedia : -1
                        },
                        success: function() {
                            Ext.widget('msjInformacion', {mensaje: 'Imagen subida satisfactoriamente.'});
                            form.reset();
                        }, failure: function(form, action) {
                            Ext.Msg.alert('Error. ', action.result.msg);
                            
                        }
                    });
                } else {
                    Ext.Msg.alert('Error. ', action.result.msg);
                }

            },
            failure: function(form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
                form.reset();
            }
        });
    },
    onClickAceptarCargarVideoSubir: function(th) {
        var me = this,
                frm = th.up('form').getForm();

        me.getWndSubirVideo().getEl().mask("Subiendo");
        frm.submit({
            url: 'subirVideo.htm',
            method: 'POST',
            params: {
                // idCompetencia: me.getCbxCompetencias().value
            },
            success: function(form) {
                me.getWndSubirVideo().getEl().unmask();
                me.getGaleriaVideos().getStore().reload();
                Ext.widget('msjInformacion', {mensaje: 'Video subido satisfactoriamente.'});
                form.reset();
              
            },
            failure: function(form, action) {
                me.getWndSubirVideo().getEl().unmask();
                Ext.Msg.alert('Error. ', action.result.msg);
                form.reset();
            }
        });
    },
    onClickCancelarCargarImagenSubir: function(th) {
        var me = this;
        th.up('window').destroy();
        if (me.getGaleriaVideos())
            me.getGaleriaVideos().getStore().reload();
    },
    onRenderGaleriaImagenes: function(th) {
        th.getStore().load();
    }
});
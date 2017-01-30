Ext.define('SGCEscalada.controller.FaseController', {
    extend: 'Ext.app.Controller',
    views: [
        'fase.MenuFase',
        'Principal',

        'fase.allround.GrdFaseAllRound',
        'competencia.CbxCompetencias',

        'fase.FrmFases',
        'fase.CbxFases',
        'fase.CbxCategorias',

        'fase.dificultad.GrdFaseDificultad',
        'fase.dificultad.GrdGeneralFaseDificultad',
        'fase.dificultad.CbxPresa',
        'fase.dificultad.CbxAgarre',

        'fase.velocidad.GrdFaseVelocidadClasificatoria',
        'fase.velocidad.GrdFaseVelocidadFinal',
        'fase.velocidad.GrdFaseVelocidadGeneral',

        'fase.bloque.GrdFaseBloqueClasificatoria',
        'fase.bloque.GrdFaseBloqueSemifinal',
        'fase.bloque.GrdFaseBloqueFinal',
        'fase.bloque.GrdFaseBloqueGeneral'
    ],
    stores: [
        'fase.dificultad.FaseDificultadStore',
        'fase.velocidad.FaseVelocidadStore',
        'fase.bloque.FaseBloqueStore',
        'fase.allround.FaseAllRoundStore'
    ],
    refs: [
        {
            ref: 'menuFase',
            selector: 'menuFase'
        },
        {
            ref: 'principal',
            selector: 'principal'
        },
        {
            ref: 'cbxPresa',
            selector: 'cbxPresa'
        },
        {
            ref: 'cbxAgarre',
            selector: 'cbxAgarre'
        },
        {
            ref: 'frmFases',
            selector: 'frmFases'
        },
        {
            ref: 'cbxCompetencias',
            selector: 'cbxCompetencias'
        },
        {
            ref: 'cbxFases',
            selector: 'cbxFases'
        },
        {
            ref: 'cbxCategorias',
            selector: 'cbxCategorias'
        },
        {
            ref: 'grdFaseDificultad',
            selector: 'grdFaseDificultad'
        },
        {
            ref: 'grdGeneralFaseDificultad',
            selector: 'grdGeneralFaseDificultad'
        },
        {
            ref: 'grdFaseVelocidadClasificatoria',
            selector: 'grdFaseVelocidadClasificatoria'
        },
        {
            ref: 'grdFaseVelocidadFinal',
            selector: 'grdFaseVelocidadFinal'
        },
        {
            ref: 'grdFaseVelocidadGeneral',
            selector: 'grdFaseVelocidadGeneral'
        },
        {
            ref: 'grdFaseBloqueClasificatoria',
            selector: 'grdFaseBloqueClasificatoria'
        },
        {
            ref: 'grdFaseBloqueSemifinal',
            selector: 'grdFaseBloqueSemifinal'
        },
        {
            ref: 'grdFaseBloqueFinal',
            selector: 'grdFaseBloqueFinal'
        },
        {
            ref: 'grdFaseAllRound',
            selector: 'grdFaseAllRound'
        },
        {
            ref: 'grdFaseBloqueGeneral',
            selector: 'grdFaseBloqueGeneral'
        }
    ],

    init: function () {
        var me = this;
        me.rankingPoints = [100,80,65,55,51,47,43,40,37,34,31,28,26,24,22,20,18,16,14,12,10,9,8,7,6,5,4,3,2,1,0];
        me.control({
            'menuFase component[action=menuFaseDificultad]': {
                click: me.onClickMenuFaseDificultad
            },
            'menuFase component[action=menuFaseDificultadGeneral]': {
                click: me.onClickMenuFaseDificultadGeneral
            },
            'menuFase component[action=menuFaseVelocidadClasificatoria]': {
                click: me.onClickMenuFaseVelocidadClasificatoria
            },
            'menuFase component[action=menuFaseVelocidadFinal]': {
                click: me.onClickMenuFaseVelocidadFinal
            },
            'menuFase component[action=menuFaseVelocidadGeneral]': {
                click: me.onClickMenuFaseVelocidadGeneral
            },
            'menuFase component[action=menuFaseBloqueClasificatoria]': {
                click: me.onClickMenuFaseBloqueClasificatoria
            },
            'menuFase component[action=menuFaseBloqueSemifinal]': {
                click: me.onClickMenuFaseBloqueSemifinal
            },
            'menuFase component[action=menuFaseBloqueFinal]': {
                click: me.onClickMenuFaseBloqueFinal
            },
            'menuFase component[action=menuFaseBloqueGeneral]': {
                click: me.onClickMenuFaseBloqueGeneral
            },
            'menuFase component[action=menuFaseAllRound]': {
                click: me.onClickMenuFaseAllRound
            },

            'frmFases component[action=cbxCompetenciasFrmFases]': {
                select: me.onSelectCbxCompetenciasFrmFases
            },
            'frmFases component[action=guardarFaseDificultad]': {
                click: me.onClickGuardarFaseDificultad
            },
            'frmFases component[action=guardarFaseDificultadGeneral]': {
                click: me.onClickGuardarFaseDificultadGeneral
            },
            'frmFases component[action=guardarFaseVelocidadClasificatoria]': {
                click: me.onClickGuardarFaseVelocidadClasificatoria
            },
            'frmFases component[action=guardarFaseVelocidadFinal]': {
                click: me.onClickGuardarFaseVelocidadFinal
            },
            'frmFases component[action=guardarFaseVelocidadGeneral]': {
                click: me.onClickGuardarFaseVelocidadGeneral
            },
            'frmFases component[action=guardarFaseBloqueClasificatoria]': {
                click: me.onClickGuardarFaseBloqueClasificatoria
            },
            'frmFases component[action=guardarFaseBloqueSemifinal]': {
                click: me.onClickGuardarFaseBloqueSemifinal
            },
            'frmFases component[action=guardarFaseBloqueFinal]': {
                click: me.onClickGuardarFaseBloqueFinal
            },
            'frmFases component[action=guardarFaseBloqueGeneral]': {
                click: me.onClickGuardarFaseBloqueGeneral
            },
            'frmFases component[action=guardarFaseAllRound]': {
                click: me.onClickGuardarFaseAllRound
            },
            'frmFases component[action=exportarAPdf]': {
                click: me.onClickExportarAPdf
            },
            'frmFases component[action=exportarAXls]': {
                click: me.onClickExportarAXls
            },

            'cbxFases': {
                select: me.onSelectCbxFasesFrmFases
            },
            'cbxCategorias': {
                select: me.onSelectCbxCategoriasFrmFases
            },

            /**GRID FASE BLOQUE*/
            'grdFaseBloqueClasificatoria': {
                edit: me.onEditGrdFaseBloqueClasificatoria
            },
            'grdFaseBloqueSemifinal': {
                edit: me.onEditGrdFaseBloqueSemifinal
            },
            'grdFaseBloqueFinal': {
                edit: me.onEditGrdFaseBloqueFinal
            },

            /**GRID FASE VELOCIDAD*/
            'grdFaseVelocidadClasificatoria': {
                edit: me.onEditGrdFaseVelocidadClasificatoria
            },
            'grdFaseVelocidadFinal': {
                edit: me.onEditGrdFaseVelocidadFinal
            },

            /**GRID FASE DIFICULTAD*/
            'grdFaseDificultad component[action=cbxPresaGrdFaseDificultad]': {
                select: me.onSelectCbxPresaGrdFaseDificultad
            },
            'grdFaseDificultad': {
                edit: me.onCompleteEditorCbxPresaFaseDificultad
            },
            'cbxPresa': {
                select: me.onSelectCbxPresaGrdFaseDificultad
            },
            'cbxAgarre': {
                select: me.onSelectCbxAgarreGrdFaseDificultad
            }
        });
    },

    onClickExportarAPdf: function(th){
        var /*me = this,*/
            frm = th.up('form'),
            grd,
            data = [];
        if(grd = frm.down('grid')){
            grd.getStore().each(function(rcd){
                data.push(rcd.getData());
            });

            frm.submit({
                url: 'exportarPdf.htm',
                method: 'GET',
                params: {
                    data: Ext.encode(data)
                },
                success: function (form, action) {
                    Ext.Msg.alert('OK', "La operaci&oacute;n se realiz&oacute; con &eacute;xito");
                   // window.location.href = "img/" + Ext.decode(action.response.responseText).src;
                    //console.log(Ext.decode(action.response.responseText));
                },
                failure: function (form, action) {
                    Ext.Msg.alert('Error. ', action.result.msg);
                }
            });
        }
    },
    onClickExportarAXls: function(th){
        var /*me = this,*/
            frm = th.up('form'),
            grd,
            data = [];
        if(grd = frm.down('grid')){
            grd.getStore().each(function(rcd){
                data.push(rcd.getData());
            });

            frm.submit({
                url: 'exportarXls.htm',
                method: 'GET',
                params: {
                    data: Ext.encode(data)
                },
                success: function (form, action) {
                     Ext.Msg.alert('OK', "La operaci&oacute;n se realiz&oacute; con &eacute;xito");
                  //  window.location.href = "img/" + Ext.decode(action.response.responseText).src;
                    //console.log(Ext.decode(action.response.responseText));
                },
                failure: function (form, action) {
                    Ext.Msg.alert('Error. ', action.result.msg);
                }
            });
        }
    },

    onSelectCbxPresaGrdFaseDificultad: function () {
//        console.log("Selecciono presa");
    },
    onSelectCbxAgarreGrdFaseDificultad: function () {
//        console.log("Selecciono agarre");
    },

    /**FORMULARIO FASES*/
    onSelectCbxCompetenciasFrmFases: function(){
        var me = this;
        me.getCbxFases().setDisabled(false);

        if(me.getCbxFases().value && me.getCbxCategorias().value){
            me.onSelectCbxCategoriasFrmFases();
        }
    },
    onSelectCbxFasesFrmFases: function(){
        var me = this;
        me.getCbxCategorias().setDisabled(false);
        if (me.getCbxFases().value && me.getCbxCategorias().value) {
            me.onSelectCbxCategoriasFrmFases();
        }
    },
    onSelectCbxCategoriasFrmFases: function(){
        var me = this,
            frm = me.getFrmFases();
        if (frm.items.items.length > 0) {
            frm.remove(frm.items.items[0]);
        }
        switch (me.getCbxFases().value){
            case 1:
                frm.add(Ext.widget('grdFaseDificultad'));
                if (frm.btnAceptarGuardarFase)
                    frm.btnAceptarGuardarFase.action = 'guardarFaseDificultad';
                me.getGrdFaseDificultad().getStore().load({
                    params:{
                        idCompetencia: me.getCbxCompetencias().value,
                        idCategoria: me.getCbxCategorias().value
                    },
                    callback: function () {
                        me.actualizarRanking(me.getGrdFaseDificultad(), 'puntos', 'DESC');
                    }
                });
                break;
            case 2:
                frm.add(Ext.widget('grdGeneralFaseDificultad'));
                if (frm.btnAceptarGuardarFase)
                    frm.btnAceptarGuardarFase.action = 'guardarFaseDificultadGeneral';
                me.getGrdGeneralFaseDificultad().getStore().load({
                    params:{
                        idCompetencia: me.getCbxCompetencias().value,
                        idCategoria: me.getCbxCategorias().value
                    },
                    callback: function () {
                        me.actualizarRanking(me.getGrdGeneralFaseDificultad(), 'puntos', 'DESC');
                    }
                });
                break;
            case 3:
                frm.add(Ext.widget('grdFaseVelocidadClasificatoria'));
                if (frm.btnAceptarGuardarFase)
                    frm.btnAceptarGuardarFase.action = 'guardarFaseVelocidadClasificatoria';
                me.getGrdFaseVelocidadClasificatoria().getStore().load({
                    params:{
                        idCompetencia: me.getCbxCompetencias().value,
                        idCategoria: me.getCbxCategorias().value,
                        idEtapa: 1
                    },
                    callback: function () {
                        me.actualizarRanking(me.getGrdFaseVelocidadClasificatoria(), 'mejorTiempo', 'ASC');
                    }
                });
                break;
            case 4:
                frm.add(Ext.widget('grdFaseVelocidadFinal'));
                if (frm.btnAceptarGuardarFase)
                    frm.btnAceptarGuardarFase.action = 'guardarFaseVelocidadFinal';
                me.getGrdFaseVelocidadFinal().getStore().load({
                    params:{
                        idCompetencia: me.getCbxCompetencias().value,
                        idCategoria: me.getCbxCategorias().value,
                        idEtapa: 1
                    },
                    callback: function () {
                        me.actualizarRanking(me.getGrdFaseVelocidadFinal(), '', '', true);
                    }
                });
                break;
            case 5:
                frm.add(Ext.widget('grdFaseVelocidadGeneral'));
                if(frm.btnAceptarGuardarFase)
                    frm.btnAceptarGuardarFase.action = 'guardarFaseVelocidadGeneral';
                me.getGrdFaseVelocidadGeneral().getStore().load({
                    params:{
                        idCompetencia: me.getCbxCompetencias().value,
                        idCategoria: me.getCbxCategorias().value
                    },
                    callback: function () {
                        me.actualizarRanking(me.getGrdFaseVelocidadGeneral(),'tiempoFinal','ASC');
                    }
                });
                break;
            case 6:
                frm.add(Ext.widget('grdFaseBloqueClasificatoria'));
                if (frm.btnAceptarGuardarFase)
                    frm.btnAceptarGuardarFase.action = 'guardarFaseBloqueClasificatoria';
                me.getGrdFaseBloqueClasificatoria().getStore().load({
                    params:{
                        idCompetencia: me.getCbxCompetencias().value,
                        idCategoria: me.getCbxCategorias().value,
                        idEtapa:1
                    },
                    callback: function () {
                        me.actualizarRanking(me.getGrdFaseBloqueClasificatoria());
                    }
                });
                break;
            case 7:
                frm.add(Ext.widget('grdFaseBloqueSemifinal'));
                if (frm.btnAceptarGuardarFase)
                    frm.btnAceptarGuardarFase.action = 'guardarFaseBloqueSemifinal';
                me.getGrdFaseBloqueSemifinal().getStore().load({
                    params:{
                        idCompetencia: me.getCbxCompetencias().value,
                        idCategoria: me.getCbxCategorias().value,
                        idEtapa: 2
                    },
                    callback: function () {
                        me.actualizarRanking(me.getGrdFaseBloqueSemifinal());
                    }
                });
                break;
            case 8:
                frm.add(Ext.widget('grdFaseBloqueFinal'));
                if (frm.btnAceptarGuardarFase)
                    frm.btnAceptarGuardarFase.action = 'guardarFaseBloqueFinal';
                me.getGrdFaseBloqueFinal().getStore().load({
                    params:{
                        idCompetencia: me.getCbxCompetencias().value,
                        idCategoria: me.getCbxCategorias().value,
                        idEtapa: 3
                    },
                    callback: function () {
                        me.actualizarRanking(me.getGrdFaseBloqueFinal());
                    }
                });
                break;
            case 9:
                frm.add(Ext.widget('grdFaseBloqueGeneral'));
                if (frm.btnAceptarGuardarFase)
                    frm.btnAceptarGuardarFase.action = 'guardarFaseBloqueGeneral';
                me.getGrdFaseBloqueGeneral().getStore().load({
                    params:{
                        idCompetencia: me.getCbxCompetencias().value,
                        idCategoria: me.getCbxCategorias().value,
                        idEtapa:3
                    },
                    callback: function () {
                        me.actualizarRanking(me.getGrdFaseBloqueGeneral());
                    }
                });
                break;
            case 'all':
                frm.add(Ext.widget('grdFaseAllRound'));
                if (frm.btnAceptarGuardarFase)
                    frm.btnAceptarGuardarFase.action = 'guardarFaseAllRound';
                me.getGrdFaseAllRound().getStore().load({
                    params:{
                        idCompetencia: me.getCbxCompetencias().value,
                        idCategoria: me.getCbxCategorias().value
                    },
                    callback: function () {
                        me.actualizarRanking(me.getGrdFaseAllRound());
                    }
                });
                break;
            default :
                break;
        }
    },


    /**FASE ALLROUND*/
    onClickGuardarFaseAllRound: function(){
        var me = this,
            frm = me.getFrmFases(),
            records = me.getGrdFaseAllRound().getStore().getRange(),
            arr = [];

        Ext.each(records, function (record) {
            arr.push({
                idCompetencia: record.data.idCompetencia,
                idDeportista: record.data.idDeportista,
                totalPuntos: record.data.totalPuntos,
                ranking: record.data.ranking,
                puntos: record.data.punto
            });
        });

        frm.submit({
            url: 'guardarAllRoundG.htm',
            method: 'GET',
            params: {
                generalARG: Ext.encode(arr)
            },
            success: function () {
                Ext.widget('msjInformacion', {mensaje:'Fase guardada satisfactoriamente.'});
               
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
            }
        });
    },

    /**FASE BLOQUE*/
    onEditGrdFaseBloqueClasificatoria: function(editor, e){
        var me = this,
            record = e.record,
            grd = me.getGrdFaseBloqueClasificatoria();

        var totalTop = (record.get('t1') ? 1 : 0) +
            (record.get('t2') ? 1 : 0) +
            (record.get('t3') ? 1 : 0) +
            (record.get('t4') ? 1 : 0) +
            (record.get('t5') ? 1 : 0);

        var intentosTop = record.get('t1') +
            record.get('t2') +
            record.get('t3') +
            record.get('t4') +
            record.get('t5');

        var totalBonos = (record.get('b1') ? 1 : 0) +
            (record.get('b2') ? 1 : 0) +
            (record.get('b3') ? 1 : 0) +
            (record.get('b4') ? 1 : 0) +
            (record.get('b5') ? 1 : 0);

        var intentosBonos = record.get('b1') +
            record.get('b2') +
            record.get('b3') +
            record.get('b4') +
            record.get('b5');

        var primeraColumnaTop_terceraColumnaBono = record.get('t1') + record.get('b3');

        var segundaColumnaBono_cuartaColumnaBono = record.get('b2') + record.get('b4');

        record.set('totalTop', totalTop);
        record.set('intentosTop', intentosTop);
        record.set('totalBonos', totalBonos);
        record.set('intentosBonos', intentosBonos);
        record.set('primeraColumnaTop_terceraColumnaBono', primeraColumnaTop_terceraColumnaBono);
        record.set('segundaColumnaBono_cuartaColumnaBono', segundaColumnaBono_cuartaColumnaBono);
        me.actualizarRanking(grd, 'totalTop', 'DESC');
    },
    onClickGuardarFaseBloqueClasificatoria: function () {
        var me = this,
            frm = me.getFrmFases(),
            records = me.getGrdFaseBloqueClasificatoria().getStore().getRange(),
            arr = [];

        Ext.each(records, function (record) {
            arr.push({
                idCompetencia: record.data.idCompetencia,
                idDeportista: record.data.idDeportista,
                t1: record.data.t1,
                t2: record.data.t2,
                t3: record.data.t3,
                t4: record.data.t4,
                t5: record.data.t5,
                b1: record.data.b1,
                b2: record.data.b2,
                b3: record.data.b3,
                b4: record.data.b4,
                b5: record.data.b5
            });
        });

        frm.submit({
            url: 'guardarFaseBloque.htm',
            method: 'GET',
            params: {
                bloque: Ext.encode(arr),
                idEtapa: 1
            },
            success: function () {
                Ext.widget('msjInformacion', {mensaje: 'Fase guardada satisfactoriamente.'});
                
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
            }
        });
    },

    onEditGrdFaseBloqueSemifinal: function(editor, e){
        var me = this,
            record = e.record,
            grd = me.getGrdFaseBloqueSemifinal();

        var totalTop = (record.get('t1') ? 1 : 0) +
            (record.get('t2') ? 1 : 0) +
            (record.get('t3') ? 1 : 0) +
            (record.get('t4') ? 1 : 0);

        var intentosTop = record.get('t1') +
            record.get('t2') +
            record.get('t3') +
            record.get('t4');

        var totalBonos = (record.get('b1') ? 1 : 0) +
            (record.get('b2') ? 1 : 0) +
            (record.get('b3') ? 1 : 0) +
            (record.get('b4') ? 1 : 0);

        var intentosBonos = record.get('b1') +
            record.get('b2') +
            record.get('b3') +
            record.get('b4');

        var primeraColumnaTop_terceraColumnaBono = record.get('t1') + record.get('b3');

        var segundaColumnaBono_cuartaColumnaBono = record.get('b2') + record.get('b4');

        record.set('totalTop', totalTop);
        record.set('intentosTop', intentosTop);
        record.set('totalBonos', totalBonos);
        record.set('intentosBonos', intentosBonos);
        record.set('primeraColumnaTop_terceraColumnaBono', primeraColumnaTop_terceraColumnaBono);
        record.set('segundaColumnaBono_cuartaColumnaBono', segundaColumnaBono_cuartaColumnaBono);
        me.actualizarRanking(grd, 'totalTop', 'DESC');
    },
    onClickGuardarFaseBloqueSemifinal: function () {
        var me = this,
            frm = me.getFrmFases(),
            records = me.getGrdFaseBloqueSemifinal().getStore().getRange(),
            arr = [];

        Ext.each(records, function (record) {
            arr.push({
                idCompetencia: record.data.idCompetencia,
                idDeportista: record.data.idDeportista,
                t1: record.data.t1,
                t2: record.data.t2,
                t3: record.data.t3,
                t4: record.data.t4,
                b1: record.data.b1,
                b2: record.data.b2,
                b3: record.data.b3,
                b4: record.data.b4
            });
        });

        frm.submit({
            url: 'guardarFaseBloque.htm',
            method: 'GET',
            params: {
                bloque: Ext.encode(arr),
                idEtapa: 2
            },
            success: function () {
                Ext.widget('msjInformacion', {mensaje: 'Fase guardada satisfactoriamente.'});
                
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
            }
        });
    },

    onEditGrdFaseBloqueFinal: function(editor, e){
        var me = this,
            record = e.record,
            grd = me.getGrdFaseBloqueFinal();

        var totalTop = (record.get('t1') ? 1 : 0) +
            (record.get('t2') ? 1 : 0) +
            (record.get('t3') ? 1 : 0) +
            (record.get('t4') ? 1 : 0);

        var intentosTop = record.get('t1') +
            record.get('t2') +
            record.get('t3') +
            record.get('t4');

        var totalBonos = (record.get('b1') ? 1 : 0) +
            (record.get('b2') ? 1 : 0) +
            (record.get('b3') ? 1 : 0) +
            (record.get('b4') ? 1 : 0);

        var intentosBonos = record.get('b1') +
            record.get('b2') +
            record.get('b3') +
            record.get('b4');

        var primeraColumnaTop_terceraColumnaBono = record.get('t1') + record.get('b3');

        var segundaColumnaBono_cuartaColumnaBono = record.get('b2') + record.get('b4');

        record.set('totalTop', totalTop);
        record.set('intentosTop', intentosTop);
        record.set('totalBonos', totalBonos);
        record.set('intentosBonos', intentosBonos);
        record.set('primeraColumnaTop_terceraColumnaBono', primeraColumnaTop_terceraColumnaBono);
        record.set('segundaColumnaBono_cuartaColumnaBono', segundaColumnaBono_cuartaColumnaBono);
        me.actualizarRanking(grd, 'totalTop', 'DESC');
    },
    onClickGuardarFaseBloqueFinal: function () {
        var me = this,
            frm = me.getFrmFases(),
            records = me.getGrdFaseBloqueFinal().getStore().getRange(),
            arr = [];

        Ext.each(records, function (record) {
            arr.push({
                idCompetencia: record.data.idCompetencia,
                idDeportista: record.data.idDeportista,
                t1: record.data.t1,
                t2: record.data.t2,
                t3: record.data.t3,
                t4: record.data.t4,
                b1: record.data.b1,
                b2: record.data.b2,
                b3: record.data.b3,
                b4: record.data.b4
            });
        });

        frm.submit({
            url: 'guardarFaseBloque.htm',
            method: 'GET',
            params: {
                bloque: Ext.encode(arr),
                idEtapa: 3
            },
            success: function () {
                Ext.widget('msjInformacion', {mensaje: 'Fase guardada satisfactoriamente.'});
              
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
            }
        });
    },

    onClickGuardarFaseBloqueGeneral: function () {
        var me = this,
            frm = me.getFrmFases(),
            records = me.getGrdFaseBloqueGeneral().getStore().getRange(),
            arr = [];

        Ext.each(records, function (record) {
            arr.push({
                idCompetencia: record.data.idCompetencia,
                idDeportista: record.data.idDeportista,
                puntosB: record.data.punto,
                rankingB: record.data.ranking
            });
        });

        frm.submit({
            url: 'guardarAllRoundBG.htm',
            method: 'GET',
            params: {
                generalBG: Ext.encode(arr)
            },
            success: function () {
                Ext.widget('msjInformacion', {mensaje: 'Fase guardada satisfactoriamente.'});
                
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
            }
        });
    },


    /**FASE VELOCIDAD*/
    onEditGrdFaseVelocidadClasificatoria: function(editor, e){
        var me = this,
            record = e.record,
            grd = me.getGrdFaseVelocidadClasificatoria(),
            t;

        var t1 = record.get('tiempo1'),
            t2 = record.get('tiempo2');

        console.log({t1:t1,t2:t2});

        if(t1 && !t2){
            t =t1;
        } else if(!t1 && t2){
            t =t2;
        } else if (t1 < t2) {
            t = t1;
        } else if (t1 > t2) {
            t = t2;
        }
        else{
            t = t1;
        }

        record.set('mejorTiempo',t);
        me.actualizarRanking(grd, 'mejorTiempo', 'ASC',true);
    },
    onClickGuardarFaseVelocidadClasificatoria: function () {
        var me = this,
            frm = me.getFrmFases(),
            records = me.getGrdFaseVelocidadClasificatoria().getStore().getRange(),
            arr = [];

        Ext.each(records, function (record) {
            arr.push({
                idCompetencia: record.data.idCompetencia,
                idDeportista: record.data.idDeportista,
                tiempo1: record.data.tiempo1,
                tiempo2: record.data.tiempo2
            });
        });

        frm.submit({
            url: 'guardarFaseVelocidad.htm',
            method: 'GET',
            params: {
                velocidad: Ext.encode(arr),
                idEtapa: 1
            },
            success: function () {
                Ext.widget('msjInformacion', {mensaje: 'Fase guardada satisfactoriamente.'});
               
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
            }
        });
    },

    onEditGrdFaseVelocidadFinal: function(){
        var me = this,
            grd = me.getGrdFaseVelocidadFinal();

        me.actualizarRanking(grd, 'tiempoFinal', 'ASC');
    },
    onClickGuardarFaseVelocidadFinal: function () {
        var me = this,
            frm = me.getFrmFases(),
            records = me.getGrdFaseVelocidadFinal().getStore().getRange(),
            arr = [];

        Ext.each(records, function (record) {
            arr.push({
                idCompetencia: record.data.idCompetencia,
                idDeportista: record.data.idDeportista,
                tiempoFinal: record.data.tiempoFinal
            });
        });

        frm.submit({
            url: 'guardarFaseVelocidad.htm',
            method: 'GET',
            params: {
                velocidad: Ext.encode(arr),
                idEtapa: 3
            },
            success: function () {
                Ext.widget('msjInformacion', {mensaje: 'Fase guardada satisfactoriamente.'});
                
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
            }
        });
    },

    onClickGuardarFaseVelocidadGeneral: function () {
        var me = this,
            frm = me.getFrmFases(),
            records = me.getGrdFaseVelocidadGeneral().getStore().getRange(),
            arr = [];

        Ext.each(records, function (record) {
            arr.push({
                idCompetencia: record.data.idCompetencia,
                idDeportista: record.data.idDeportista,
                puntosV: record.data.punto,
                rankingV: record.data.ranking
            });
        });

        frm.submit({
            url: 'guardarAllRoundVG.htm',
            method: 'GET',
            params: {
                generalVG: Ext.encode(arr)
            },
            success: function () {
                Ext.widget('msjInformacion', {mensaje: 'Fase guardada satisfactoriamente.'});
               
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
            }
        });
    },


    /**FASE DIFICULTAD*/
    onCompleteEditorCbxPresaFaseDificultad: function (editor, e) {
        /*Parameters
         editor : Ext.grid.plugin.CellEditing
         e : Object
         An edit event with the following properties:

         grid - The grid
         record - The record that was edited
         field - The field name that was edited
         value - The value being set
         originalValue - The original value for the field, before the edit.
         row - The grid table row
         column - The grid Column defining the column that was edited.
         rowIdx - The row index that was edited
         colIdx - The column index that was edited*/
        var me = this,
            record = e.record,
            grd = me.getGrdFaseDificultad();

        var puntos = record.get('puntos'),
            presa = record.get('presa'),
            agarre = record.get('agarre');

        if (e.field == "agarre") {
            if (e.value == '+' && puntos < (presa + 0.2)) {
                record.set('puntos', presa + 0.2);
            }
            else if (!e.value && presa == (puntos - 0.2)) {
                record.set('puntos', presa);
            }
        }
        if (e.field == "presa") {
            if (agarre == '+') {
                record.set('puntos', presa + 0.2);
            }
            else {
                record.set('puntos', presa);
            }
        }

        me.actualizarRanking(grd, 'puntos', 'DESC');
    },
    onClickGuardarFaseDificultad: function() {
        var me = this,
            frm = me.getFrmFases(),
            records = me.getGrdFaseDificultad().getStore().getRange(),
            arr = [];

        Ext.each(records, function (record) {
            arr.push({
                idCompetencia: record.data.idCompetencia,
                idDeportista: record.data.idDeportista,
                idCategoria: record.data.idCategoria,
                presa: record.data.presa,
                agarre: record.data.agarre,
                puntos: record.data.puntos,
                finalizada: record.data.finalizada
            });
        });

        frm.submit({
            url: 'guardarFaseDificultad.htm',
            method: 'GET',
            params: {
                dificultad: Ext.encode(arr)
            },
            success: function () {
                Ext.widget('msjInformacion', {mensaje: 'Fase guardada satisfactoriamente.'});
               
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
            }
        });
    },

    onClickGuardarFaseDificultadGeneral: function() {
        var me = this,
            frm = me.getFrmFases(),
            records = me.getGrdGeneralFaseDificultad().getStore().getRange(),
            arr = [];

        Ext.each(records, function (record) {
            arr.push({
                idCompetencia: record.data.idCompetencia,
                idDeportista: record.data.idDeportista,
                puntosD: record.data.punto,
                rankingD: record.data.ranking
            });
        });

        frm.submit({
            url: 'guardarAllRoundDG.htm',
            method: 'GET',
            params: {
                generalDG: Ext.encode(arr)
            },
            success: function () {
                Ext.widget('msjInformacion', {mensaje: 'Fase guardada satisfactoriamente.'});
                
            },
            failure: function (form, action) {
                Ext.Msg.alert('Error. ', action.result.msg);
            }
        });
    },


    /**AUXILIARES*/
    actualizarRanking: function(grd, atributo, direccion, time){
        var me = this;

        if(time){
            grd.store.sort([
                {property: 'mejorTiempo', direction: 'ASC'},
                {property: 'tiempo1', direction: 'ASC'},
                {property: 'tiempo2', direction: 'ASC'}
            ]);
        }
        if(grd.bloque){
            grd.store.sort([
                {property: 'totalTop', direction: 'DESC'},
                {property: 'primeraColumnaTop_terceraColumnaBono', direction: 'DESC'},
                {property: 'segundaColumnaBono_cuartaColumnaBono', direction: 'ASC'}
            ]);
        }
        else{
            grd.store.sort([{property: atributo, direction: direccion}]);
        }
        /*if (time) {
         grd.getStore().each(function(r1, i){
         grd.getStore().each(function (r2, j) {
         if((!r1||!r1.data.mejorTiempo) && (r2 && r2.data.mejorTiempo)){
         var t1 = r1,
         t2 = r2;
         grd.getStore().insert(i, r2);
         grd.getStore().removeAt(i+1);
         grd.getStore().insert(j, r1);
         grd.getStore().removeAt(j);
         }
         else if((r1 && r1.data.mejorTiempo) && (r2 && r2.data.mejorTiempo )&&  r1.data.mejorTiempo< r2.data.mejorTiempo){
         grd.getStore().insert(i, r2);
         grd.getStore().removeAt(i + 1);
         grd.getStore().insert(j, r1);
         grd.getStore().removeAt(j);
         }
         });
         });
         }*/
        var cont = 0;
        grd.getStore().each(function (rcd, i) {
            if(time){
                if(rcd.data.mejorTiempo){
                    rcd.set('ranking', cont + 1);
                    cont++;
                }
                else{
                    rcd.set('ranking', '');
                }

            }
            else{
                rcd.set('ranking', i + 1);
            }

            if(grd.general){
                if (rcd.data.ranking == 1) {
                    rcd.set('medalla', "ORO");
                }
                else if (rcd.data.ranking == 2) {
                    rcd.set('medalla', "PLATA");
                }
                else if (rcd.data.ranking == 3) {
                    rcd.set('medalla', "BRONCE");
                }

                if(i<30){
                    rcd.set('punto', me.rankingPoints[i]);
                }
                else{
                    rcd.set('punto', me.rankingPoints[30]);
                }
            }
        });
    },

    onClickMenuFaseDificultad: function (th) {
        var me = this;
        if (me.getPrincipal().items.items.length > 0) {
            me.getPrincipal().remove(me.getPrincipal().items.items[0]);
        }
        me.getPrincipal().add(Ext.widget('frmFases',{cliente: th.up('button').cliente}));
    },
    onClickMenuFaseDificultadGeneral: function () {
        var me = this;
        me.getPrincipal().add(Ext.widget('grdGeneralFaseDificultad'));
    },
    onClickMenuFaseVelocidadClasificatoria: function () {
        var me = this;
        me.getPrincipal().add(Ext.widget('grdFaseVelocidadClasificatoria'));
    },
    onClickMenuFaseVelocidadFinal: function () {
        var me = this;
        me.getPrincipal().add(Ext.widget('grdFaseVelocidadFinal'));
    },
    onClickMenuFaseVelocidadGeneral: function () {
        var me = this;
        me.getPrincipal().add(Ext.widget('grdFaseVelocidadGeneral'));
    },
    onClickMenuFaseBloqueClasificatoria: function () {
        var me = this;
        me.getPrincipal().add(Ext.widget('grdFaseBloqueClasificatoria'));
    },
    onClickMenuFaseBloqueSemifinal: function () {
        var me = this;
        me.getPrincipal().add(Ext.widget('grdFaseBloqueSemifinal'));
    },
    onClickMenuFaseBloqueFinal: function () {
        var me = this;
        me.getPrincipal().add(Ext.widget('grdFaseBloqueFinal'));
    },
    onClickMenuFaseBloqueGeneral: function () {
        var me = this;
        me.getPrincipal().add(Ext.widget('grdFaseBloqueGeneral'));
    },
    onClickMenuFaseAllRound: function () {
        var me = this;
        me.getPrincipal().add(Ext.widget('grdFaseAllRound'));
    }
});
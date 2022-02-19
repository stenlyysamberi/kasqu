<?php

namespace App;
use Illuminate\Support\Facades\DB;
use Illuminate\Database\Eloquent\Model;

class KasPemasukan extends Model
{
    protected $guarded = [];
    protected $table = 'tbl_kasmasuk';
    protected $primaryKey = 'kasmasuk_id';
    // public $timestamps = false;


    /**
     * Get the user that owns the KasPemasukan
     *
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    static function mitras(){
        $mitra = DB::table('tbl_kasmasuk')
        ->join('tbl_user','tbl_kasmasuk.user_id','=','tbl_user.user_id')
        ->join('tbl_mitra_kampus','tbl_kasmasuk.mitra_id','=','tbl_mitra_kampus.mitra_id');
        return $mitra;
    }
}

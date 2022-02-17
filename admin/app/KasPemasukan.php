<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class KasPemasukan extends Model
{
    protected $guarded = [];
    protected $table = 'tbl_kasmasuk';
    protected $primaryKey = 'kasmasuk_id';
    public $timestamps = false;


    /**
     * Get the user that owns the KasPemasukan
     *
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function users()
    {
        return $this->belongsTo(User::class);
    }
}

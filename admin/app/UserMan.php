<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class UserMan extends Model
{
    protected $guarded = [];
    protected $table = 'tbl_user';
    protected $primaryKey = 'user_id';
    public $timestamps = false;
    // protected $connection = 'db_kasqu';

    // Retrieve the first model matching the query constraints...
   
}

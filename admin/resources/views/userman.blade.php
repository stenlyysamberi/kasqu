@extends('main.index')
@section('menu')
<div class="row">
    <div class="col-12">
        <div class="page-title-box">
            <div class="page-title-right">
                <ol class="breadcrumb m-0">
                    <li class="breadcrumb-item"><a href="javascript: void(0);">{{ $menu1 }}</a></li>
                    <li class="breadcrumb-item active">{{ $menu2 }}</li>
                </ol>
            </div>
            <h4 class="page-title">Dashboard</h4>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-12">
        <div class="card-box">
            <div class="row">
                <div class="col-lg-8">
                    <form method="POST" class="form-inline" action="#">
                        <div class="form-group">
                            <label for="inputPassword2" class="sr-only">Search</label>
                            <input type="search" name="keyword" class="form-control" id="inputPassword2" placeholder="Search...">
                        </div>
                        <div class="form-group mx-sm-3">
                            <label for="status-select" class="mr-2">Sort By</label>
                            <select class="custom-select" id="status-select">
                                <option selected="">Semua</option>
                               
                                
                            </select>
                        </div>
                    </form>
                </div>
                <div class="col-lg-4">
                    <div class="text-lg-right mt-3 mt-lg-0">
                        <button type="button" class="btn btn-success waves-effect waves-light mr-1"><i class="mdi mdi-cog"></i></button>

                        <a data-toggle="modal" data-target="#jenismodel" href="#" class="btn btn-danger waves-effect waves-light"><i class="mdi mdi-plus-circle mr-1"></i> New</a>
                    </div>
                </div><!-- end col-->
            </div> <!-- end row -->
        </div> <!-- end card-box -->
    </div> <!-- end col-->
</div>

<div class="row">
    <div class="col-12">
       <div class="card-box">
        <table class="table table-hover">
            <thead>
                <tr>
                  <th scope="col">No</th>
                  <th scope="col">Jenis Product</th>
                  <th scope="col">Created at</th>
                  <th scope="col">Updated at</th>
                  <th scope="col">Options</th>
                </tr>
              </thead>

              {{-- <tbody>
                @foreach ($jenis as $item)
                <tr>
                    <th scope="row"> {{ $loop->iteration }}</th>
                    <td>{{ $item->nama }}</td>
                    <td>{{ $item->created_at }}</td>
                    <td>{{ $item->updated_at }}</td>
                    <td>
                        <div class="row">
                            <div class="btn-group">
                                <button class="btn btn-secondary btn-sm"><i class="fas fa-edit " aria-hidden="true"></i></button>
                               
                                <form action="/del_jenis/{{ $item->id_jenis }}" method="post">
                                    @csrf
                                    @method('delete')
                                    <button onclick="return confirm('apakah Anda akan menghapus data ini?')" class="btn btn-danger btn-sm"><i class="fas fa-trash" aria-hidden="true"></i></button>
                                </form>
                            </div>
                        </div>
                    </td>
                  </tr>
                @endforeach
              </tbody> --}}
        </table>
       </div>
    </div>

</div>


{{-- Section Modal add --}}
{{-- <form method="POST" action="/create_jenis" enctype="multipart/form-data">
    @csrf
    <div id="jenismodel" class="modal fade model-xl" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Add Jenis</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body p-4">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="field-3" class="control-label">Jenis</label>
                                <input type="text" maxlength="30" name="nama" required="required" class="form-control" id="field-3" placeholder="Enter Text">
                            </div>
                        </div>
                    </div>
                  
               </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary waves-effect" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-info waves-effect waves-light">Ok, Simpan</button>
            </div>
        </div>
    </div>
</form> --}}
@endsection
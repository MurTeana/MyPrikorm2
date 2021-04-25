using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MyPrikormWebAPI.Model.DB.Entities;
using MyPrikormWebAPI.Model.DB.Context;

namespace MyPrikormWebAPI.Interfaces
{
    interface IRepositoryUser
    {
        Task<List<User>> GetAll();
        Task<User> Get(int id);
        User Create(User user);
        User Update(User user);
        User Delete(int id);
    }
}
